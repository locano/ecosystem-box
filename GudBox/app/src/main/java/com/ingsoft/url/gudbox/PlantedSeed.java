package com.ingsoft.url.gudbox;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;

/**
 * Created by grand on 10/13/2016.
 */

public class PlantedSeed extends SugarRecord {
    @Unique
    int planted_id;
    int slot;
    ServerSeed serverSeed;
    Date plantedDate;

    public PlantedSeed(){}

    public PlantedSeed(int planted_id, ServerSeed serverSeed, Date plantedDate, int slot){
        this.planted_id = planted_id;
        this.serverSeed = serverSeed;
        this.plantedDate = plantedDate;
        this.slot = slot;
    }
}
