package com.ingsoft.url.gudbox;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grand on 10/13/2016.
 */

public class InternalAPI implements _API_Connection.onTaskCompleted {
    String baseURL = "http://urlayd.azurewebsites.net/api/", allSeedsURL = "getseeds/", specificSeedURL = "getseed/";
    OPERATIONS operations;

    public enum OPERATIONS {
        DOWNLOAD_ALL_SEEDS, DOWNLOAD_SPECIFIC_SEED
    }

    public PlantedSeed plantSeed(ServerSeed serverSeed, int slot){
        List<PlantedSeed> allPlantedSeeds = PlantedSeed.listAll(PlantedSeed.class);
        PlantedSeed plantedSeed = new PlantedSeed(allPlantedSeeds.size(), serverSeed, new Date(), slot);
        plantedSeed.save();
        return plantedSeed;
    }

    public void downloadAllSeeds(){
        _API_Connection api = new _API_Connection(InternalAPI.this);
        api.changeURL(baseURL + allSeedsURL);
        api.operation_code = operations.DOWNLOAD_ALL_SEEDS;
        api.execute();
    }

    public void downloadSeedWithId(int id){
        _API_Connection api = new _API_Connection(InternalAPI.this);
        api.changeURL(baseURL + specificSeedURL + String.valueOf(id));
        api.operation_code = operations.DOWNLOAD_SPECIFIC_SEED;
        api.execute();
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

    @Override
    public void onTaskCompleted(String response, InternalAPI.OPERATIONS code) {
        switch (code){
            case DOWNLOAD_ALL_SEEDS:
                Log.i("GUDBOX", "FINISHED DOWNLOADING ALL SEEDS");
                List<Integer> ids = getAllIdsFromJSON(response);
                for (int id: ids) {
                    List<ServerSeed> foundSeeds = ServerSeed.find(ServerSeed.class, "id = ?", String.valueOf(id));
                    if (foundSeeds.size() == 0){
                        downloadSeedWithId(id);
                    }
                }
                break;
            case DOWNLOAD_SPECIFIC_SEED:

                break;
        }
    }
// int id, int min_temp, int max_temp, int days, int volume, int min_depth, int min_radius, int min_radius_line,
// int sun_hours, int water_level, String name, String dirt, String germination, boolean needs_hotbead, boolean summer,
// boolean spring, boolean autumn, boolean winter, double ph){
    private ServerSeed serverSeedFromJSON(String response){
        try {
            JSONObject seed = new JSONObject(response);
            boolean testBool = seed.getBoolean("spring");
            ServerSeed serverSeed = new ServerSeed(
                    seed.getInt("id"),
                    seed.getInt("min_temp"),
                    seed.getInt("max_temp"),
                    seed.getInt("days"),
                    seed.getInt("volume"),
                    seed.getInt("min_depth"),
                    seed.getInt("min_radius"),
                    seed.getInt("min_radius_line"),
                    seed.getInt("sun_hours"),
                    seed.getInt("water_level"),
                    seed.getString("name"),
                    seed.getString("dirt"),
                    seed.getString("germination"),
                    seed.getBoolean("needs_hotbead"),
                    seed.getBoolean("summer"),
                    seed.getBoolean("spring"),
                    seed.getBoolean("autumn"),
                    seed.getBoolean("winter"),
                    seed.getDouble("ph")
            );
            return serverSeed;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> getAllIdsFromJSON(String response){
        List<Integer> ids = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject seedObject = jsonArray.getJSONObject(i);
                int id = seedObject.getInt("id");
                ids.add(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ids;
    }
}
