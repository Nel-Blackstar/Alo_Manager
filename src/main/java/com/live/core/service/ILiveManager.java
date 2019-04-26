package com.live.core.service;

import com.live.core.entities.Users;

public interface ILiveManager {
    public Users userConnecte();

    public char getRandomLetterUppercase();
    public char getRandomLetterLowercase();

    public String genererLoginUser();
    public String genererPasswordUser();
    public String crypterPasswordUser(String password);
}
