/*
 * Copyright (c) 2022.
 */

package fr.alng.footapi.scheduler;

import fr.alng.footapi.converter.ModelConverter;
import fr.alng.footapi.dto.BetDTO;
import fr.alng.footapi.dto.MatchDTO;
import fr.alng.footapi.model.Bet;
import fr.alng.footapi.model.Match;
import fr.alng.footapi.model.Team;
import fr.alng.footapi.service.BetService;
import fr.alng.footapi.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class VictoryRoutine {

    ModelConverter<Bet, BetDTO> betConverter = new ModelConverter<>();
    ModelConverter<Match, MatchDTO> matchConverter = new ModelConverter<>();
    private Long matchId;

    public void run(BetService betService, MatchService matchService){
        List<Bet> bets = betService.findBetsWithoutResults();
        bets.forEach((Bet bet) -> {
            if(Objects.equals(matchService.getMatch(bet.getMatche().getId()).get().getStatus(), "FINISHED")){
                matchId = bet.getMatche().getApiId();
                setWinnerTeam(matchService);
                bet.setHasWin(Objects.equals(bet.getTeam().getId(), bet.getMatche().getWinnerTeam().getId()));
                BetDTO betDTO = betConverter.convertEntityToDto(bet, BetDTO.class);
                betService.saveBet(betDTO);
            }
        });
    }

    public void setWinnerTeam(MatchService matchService){
        Match match = matchService.getMatchByApiId(matchId);
        Integer homeScore = match.getHomeScore();
        Integer awayScore = match.getAwayScore();
        if(homeScore > awayScore){
            match.setWinnerTeam(match.getHomeTeam());
        } else if (homeScore < awayScore) {
            match.setWinnerTeam(match.getAwayTeam());
        } else {
            match.setWinnerTeam(new Team(2L));
        }
        MatchDTO matchDTO = matchConverter.convertEntityToDto(match, MatchDTO.class);
        //matchDTO.setRooms(match.getRooms());
        matchService.saveMatch(matchDTO);
    }
}
