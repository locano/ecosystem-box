package com.ingsoft.url.gudbox;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by grand on 10/13/2016.
 */

public class InternalAPI implements _API_Connection.onTaskCompleted {
    String baseURL = "http://urlayd.azurewebsites.net/api/", allSeedsURL = "getseeds/", specificSeedURL = "getseed/";
    OPERATIONS operations;

    public enum OPERATIONS {
        DOWNLOAD_ALL_SEEDS, DOWNLOAD_SPECIFIC_SEED
    }

    public List<ServerSeed> getAllServerSeeds(){
        return ServerSeed.listAll(ServerSeed.class);
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

    private void downloadSeedWithId(int id){
        _API_Connection api = new _API_Connection(InternalAPI.this);
        api.changeURL(baseURL + specificSeedURL + String.valueOf(id));
        api.operation_code = operations.DOWNLOAD_SPECIFIC_SEED;
        api.addParam("id", id);
        Log.i("GUDBOX", "DOWNLOADING SEED DETAIL FOR ID " + String.valueOf(id));
        api.execute();
    }

    @Override
    public void onTaskCompleted(String response, InternalAPI.OPERATIONS code, Map<String, Object> params) {
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
                Log.i("GUDBOX", "SAVED ALL SERVER SEEDS ON PHONE");
                break;
            case DOWNLOAD_SPECIFIC_SEED:
                Log.i("GUDBOX", "FINISHED DOWNLOADING SPECIFIC SEED " + String.valueOf((Integer) params.get("id")));
                ServerSeed serverSeed = serverSeedFromJSON(response, (Integer) params.get("id"));
                serverSeed.save();
                Log.i("GUDBOX", "SAVED SEED " + String.valueOf((Integer) params.get("id") + " ON THE PHONE"));
                break;
        }
    }

    private ServerSeed serverSeedFromJSON(String response, int id){
        try {
            JSONObject seed = new JSONObject(response);
            ServerSeed serverSeed = new ServerSeed(
                    id,
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
                    seed.getString("needs_hotbead").equalsIgnoreCase("T"),
                    seed.getString("summer").equalsIgnoreCase("T"),
                    seed.getString("spring").equalsIgnoreCase("T"),
                    seed.getString("autumn").equalsIgnoreCase("T"),
                    seed.getString("winter").equalsIgnoreCase("T"),
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
