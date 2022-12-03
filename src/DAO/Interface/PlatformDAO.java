package DAO.Interface;

import DAO.Entity.Platform;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface PlatformDAO {
    void addPlatform(Platform platform);
    void updatePlatform(Platform platform);
    void deletePlatform(String platformID);
    Platform getPlatform(String platformID);
    List<Platform> findPlatforms(Platform platform);
}
