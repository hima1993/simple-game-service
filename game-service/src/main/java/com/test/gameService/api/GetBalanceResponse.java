/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.gameService.api;

import com.test.gameService.models.Player;
import java.util.Optional;

/**
 *
 * @author himaruksilva
 */
public class GetBalanceResponse 
{
    public final Double amount;
    public final String error;
    
    public GetBalanceResponse(Double amount, String error)
    {
        this.amount = amount;
        this.error = error;
    }
    
}
