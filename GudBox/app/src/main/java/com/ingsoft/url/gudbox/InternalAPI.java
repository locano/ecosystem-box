package com.ingsoft.url.gudbox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by grand on 10/13/2016.
 */

public class InternalAPI {
    public PlantedSeed plantSeed(ServerSeed serverSeed, int slot){
        List<PlantedSeed> allPlantedSeeds = PlantedSeed.listAll(PlantedSeed.class);
        PlantedSeed plantedSeed = new PlantedSeed(allPlantedSeeds.size(), serverSeed, new Date(), slot);
        plantedSeed.save();
        return plantedSeed;
    }

    public void downloadSeeds(){
        List<ServerSeed> serverSeeds = new ArrayList<ServerSeed>();
        //server magic
        /*ServerSeed seed1 = new ServerSeed(1, 0, 0, 90, 0, 0, 0, 0, 4, 1, "Semilla 1", "compost", "test 1", false, false, false, true, true, 7.1);
        ServerSeed seed2 = new ServerSeed(1, 0, 0, 90, 0, 0, 0, 0, 5, 2, "Semilla 2", "compost", "test 2", false, false, false, true, true, 7.1);
        ServerSeed seed3 = new ServerSeed(1, 0, 0, 90, 0, 0, 0, 0, 6, 3, "Semilla 3", "compost", "test 3", false, false, false, true, true, 7.1);

        serverSeeds.add(seed1);
        serverSeeds.add(seed2);
        serverSeeds.add(seed3);*/

        saveUnsavedSeeds(serverSeeds);
    }

    public List<ServerSeed> getAllSeeds(){
        return ServerSeed.listAll(ServerSeed.class);
    }

    private void saveUnsavedSeeds(List<ServerSeed> serverSeeds){
        List<ServerSeed> savedSeeds = ServerSeed.listAll(ServerSeed.class);
        if(savedSeeds.size() == 0){
            for (ServerSeed serverSeed : serverSeeds) {
                serverSeed.save();
            }
        }
    }
}
