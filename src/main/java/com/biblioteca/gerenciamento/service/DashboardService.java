package com.biblioteca.gerenciamento.service;

import com.biblioteca.gerenciamento.domain.dto.DashboardDTO;
import com.biblioteca.gerenciamento.domain.dto.StatusEmprestimo;
import com.biblioteca.gerenciamento.domain.entity.Emprestimo;
import com.biblioteca.gerenciamento.domain.entity.Estoque;
import com.biblioteca.gerenciamento.repository.EmprestimoRepository;
import com.biblioteca.gerenciamento.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EmprestimoRepository emprestimoRepository;
    private final EstoqueRepository estoqueRepository;

    public DashboardDTO getDashboardStats() {
        List<Estoque> estoques = estoqueRepository.findAll();
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        LocalDate hoje = LocalDate.now();

        // 1. Stats de Livros e Condições
        int totalBooks = 0;
        int qtdNovo = 0, qtdConservado = 0, qtdMalConservado = 0, qtdInutilizado = 0;

        for (Estoque est : estoques) {
            totalBooks += est.getQtdTotal() != null ? est.getQtdTotal() : 0;
            qtdNovo += est.getQtdNovo() != null ? est.getQtdNovo() : 0;
            qtdConservado += est.getQtdConservado() != null ? est.getQtdConservado() : 0;
            qtdMalConservado += est.getQtdMalConservado() != null ? est.getQtdMalConservado() : 0;
            qtdInutilizado += est.getQtdInutilizado() != null ? est.getQtdInutilizado() : 0;
        }

        List<DashboardDTO.ConditionData> conditionStats = Arrays.asList(
                new DashboardDTO.ConditionData("Novo", qtdNovo),
                new DashboardDTO.ConditionData("Conservado", qtdConservado),
                new DashboardDTO.ConditionData("Mal Conservado", qtdMalConservado),
                new DashboardDTO.ConditionData("Inutilizado", qtdInutilizado)
        );

        // 2. Processar Empréstimos (Atrasos e Próximos)
        int activeLoans = 0;
        Set<Integer> alunosComAtraso = new HashSet<>();
        List<DashboardDTO.PendingReturnDTO> overdueLoans = new ArrayList<>();
        List<DashboardDTO.UpcomingReturnDTO> upcomingReturns = new ArrayList<>();

        for (Emprestimo emp : emprestimos) {
            if (StatusEmprestimo.EMPRESTADO.equals(emp.getStatusEmprestimo()) || 
                StatusEmprestimo.ATRASADO.equals(emp.getStatusEmprestimo())) {
                
                activeLoans++;
                LocalDate prazo = emp.getDataEntrega().plusDays(15);
                long diasRestantes = ChronoUnit.DAYS.between(hoje, prazo);

                String dueDateStr = prazo.format(DateTimeFormatter.ofPattern("dd/MM"));

                if (diasRestantes < 0) {
                    alunosComAtraso.add(emp.getAluno().getId());
                    overdueLoans.add(new DashboardDTO.PendingReturnDTO(
                            emp.getId(),
                            emp.getAluno().getNome(),
                            emp.getLivro().getTitulo(),
                            (int) Math.abs(diasRestantes),
                            dueDateStr
                    ));
                } else if (diasRestantes <= 10) {
                    upcomingReturns.add(new DashboardDTO.UpcomingReturnDTO(
                            emp.getId(),
                            emp.getAluno().getNome(),
                            emp.getLivro().getTitulo(),
                            (int) diasRestantes,
                            dueDateStr
                    ));
                }
            }
        }

        int overdueReturnsCount = overdueLoans.size();
        int pendingStudentsCount = alunosComAtraso.size();

        // 3. Movimentação Mensal (Últimos 6 meses)
        List<DashboardDTO.ChartData> monthlyMovement = new ArrayList<>();
        DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMM", new Locale("pt", "BR"));

        for (int i = 5; i >= 0; i--) {
            LocalDate refDate = hoje.minusMonths(i);
            int year = refDate.getYear();
            int month = refDate.getMonthValue();
            
            long loansInMonth = emprestimos.stream()
                    .filter(e -> e.getDataEntrega() != null && 
                                 e.getDataEntrega().getYear() == year && 
                                 e.getDataEntrega().getMonthValue() == month)
                    .count();

            long returnsInMonth = emprestimos.stream()
                    .filter(e -> e.getDataDevolucao() != null && 
                                 e.getDataDevolucao().getYear() == year && 
                                 e.getDataDevolucao().getMonthValue() == month)
                    .count();

            String monthName = refDate.format(monthFmt);
            monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
            
            monthlyMovement.add(new DashboardDTO.ChartData(monthName, (int) loansInMonth, (int) returnsInMonth));
        }

        // Ordenar listas para exibição
        overdueLoans.sort((a, b) -> b.getDaysOverdue().compareTo(a.getDaysOverdue())); // Maior atraso primeiro
        upcomingReturns.sort((a, b) -> a.getDaysLeft().compareTo(b.getDaysLeft())); // Menor prazo primeiro

        return DashboardDTO.builder()
                .totalBooks(new DashboardDTO.StatCard(totalBooks))
                .activeLoans(new DashboardDTO.StatCard(activeLoans))
                .overdueReturns(new DashboardDTO.StatCard(overdueReturnsCount))
                .pendingStudents(new DashboardDTO.StatCard(pendingStudentsCount))
                .conditionStats(conditionStats)
                .overdueLoans(overdueLoans)
                .upcomingReturns(upcomingReturns)
                .monthlyMovement(monthlyMovement)
                .build();
    }
}
