package com.ingsoft.url.gudbox;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by grand on 10/13/2016.
 */

public class ServerSeed extends SugarRecord {
    @Unique
    int id;
    int min_temp, max_temp, days, volume, min_depth, min_radius, min_radius_line, sun_hours, water_level;
    String name, dirt, germination;
    boolean needs_hotbead, spring, summer, autumn, winter;
    double ph;

    public ServerSeed(){}

    public ServerSeed(int id, int min_temp, int max_temp, int days, int volume, int min_depth, int min_radius, int min_radius_line, int sun_hours, int water_level, String name, String dirt, String germination, boolean needs_hotbead, boolean summer, boolean spring, boolean autumn, boolean winter, double ph){
        this.id = id;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.days = days;
        this.volume = volume;
        this.min_depth = min_depth;
        this.min_radius = min_radius;
        this.min_radius_line = min_radius_line;
        this.sun_hours = sun_hours;
        this.water_level = water_level;
        this.name = name;
        this.dirt = dirt;
        this.germination = germination;
        this.needs_hotbead = needs_hotbead;
        this.summer = summer;
        this.spring = spring;
        this.winter = winter;
        this.autumn = autumn;
        this.ph = ph;
    }
}
