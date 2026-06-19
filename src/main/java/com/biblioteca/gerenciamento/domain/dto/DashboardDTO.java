package com.biblioteca.gerenciamento.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private StatCard totalBooks;
    private StatCard activeLoans;
    private StatCard overdueReturns;
    private StatCard pendingStudents;

    private List<ChartData> monthlyMovement;
    private List<ConditionData> conditionStats;

    private List<PendingReturnDTO> overdueLoans;
    private List<UpcomingReturnDTO> upcomingReturns;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatCard {
        private Integer value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChartData {
        private String month;
        private Integer loans;
        private Integer returns;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConditionData {
        private String label;
        private Integer value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PendingReturnDTO {
        private Integer id;
        private String studentName;
        private String bookTitle;
        private Integer daysOverdue;
        private String dueDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpcomingReturnDTO {
        private Integer id;
        private String studentName;
        private String bookTitle;
        private Integer daysLeft;
        private String dueDate;
    }
}
