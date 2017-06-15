package PMS.PlayerMatchSysteem.model;
// Created on 1-6-2017.

import java.sql.Date;

public class Tournament {
    private int Tournament_id;
    private Date StartDate;
    private Date EndDate;

    public Tournament(int tournament_id, Date startDate, Date endDate) {
        Tournament_id = tournament_id;
        StartDate = startDate;
        EndDate = endDate;
    }

    public Tournament() {
    }

}
