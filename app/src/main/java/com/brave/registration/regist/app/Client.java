package com.brave.registration.regist.app;

import java.io.IOException;

import okhttp3.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {
    private final OkHttpClient httpClient = new OkHttpClient();
    public String token;
    private String auth;


    public Client() {
        this.token = "";
        this.auth = Credentials.basic("alon@gmail.com", "azor");
    }


    public JSONObject findUserByID(String id) throws IOException, JSONException {
        String url = "http://localhost:5000/user/"+id;
        Request request = new Request.Builder()
                .url(url)
                .build();
        return new JSONObject(this.httpClient.newCall(request).execute().body().string());
    }


    public String refreshToken() throws IOException, JSONException {
        String url = "http://localhost:5000/token";
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", this.auth)
                .build();
        String res = this.httpClient.newCall(request).execute().body().string();
        JSONObject data = new JSONObject(res);
        this.token = data.get("token").toString();
        return this.token;
    }


    public JSONObject createUser(String firstName, String lastName, String city, String email, String phone) throws IOException, JSONException {
        String url = "http://localhost:5000/user";
        RequestBody formBody = new FormBody.Builder()
                .add("first_name", firstName)
                .add("last_name", lastName)
                .add("password", city)
                .add("email", email)
                .add("cellphone", phone)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return new JSONObject(this.httpClient.newCall(request).execute().body().string());
    }


    public JSONObject createEvent(String [] tags, String title, String description,  String startTime, String endTime, int reward, String geolocation) throws IOException, JSONException {
        String url = "http://localhost:5000/events";
        JSONObject obj = new JSONObject();
        obj = obj.put("tags", tags);

        obj = obj.put("title", title);
        if (!description.equals("")) {
            obj = obj.put("description", description);
        }
        if (!startTime.equals("")) {
            obj = obj.put("start_time", startTime);
        }
        if (!endTime.equals("")) {
            obj = obj.put("end_time", endTime);
        }
        if(reward!=-1){
            obj = obj.put("reward", String.valueOf(reward));
        }
        if(!geolocation.equals("")){
            obj = obj.put("geolocation", geolocation);
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody payload = RequestBody.create(obj.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", this.auth)
                .post(payload)
                .build();
        Response response = this.httpClient.newCall(request).execute();
        return new JSONObject(response.body().string());
    }


    public JSONObject updateEvent(String id, String tags, String title, String description, String startTime, String endTime, int reward) throws IOException, JSONException {
        String url = "http://localhost:5000/events/"+id;
        JSONObject obj = new JSONObject();
        obj.put("message", "updated");
        obj.put("event_data", new JSONObject());
        obj.getJSONObject("event_data").put("tags", tags);
        if(!title.equals("")){
            obj.getJSONObject("event_data").put("title", title);
        }
        if(!description.equals("")){
            obj.getJSONObject("event_data").put("description", description);
        }
        if(!startTime.equals("")){
            obj.getJSONObject("event_data").put("start_time", startTime);
        }
        if(!endTime.equals("")){
            obj.getJSONObject("event_data").put("end_time", endTime);
        }
        if(reward != -1){
            obj.getJSONObject("event_data").put("reward", reward);
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody payload = RequestBody.create(obj.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", this.auth)
                .patch(payload)
                .build();
        Response response = this.httpClient.newCall(request).execute();
        return new JSONObject(response.body().string());
    }

    public boolean deleteEvent(String id, String description) throws IOException, JSONException {
        String url = "http://localhost:5000/events/"+id;
        JSONObject obj = new JSONObject();
        obj.put("message", description);
        obj.put("canceled", "cancel");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody payload = RequestBody.create(obj.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .addHeader("Authorization", this.auth)
                .delete(payload)
                .build();
        Response response = this.httpClient.newCall(request).execute();
        return !response.body().string().equals("");
    }


    public String deleteUser(String id) throws IOException {
        String url = "http://localhost:5000/user/"+id;
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Response response = this.httpClient.newCall(request).execute();
        return response.body().string();
    }

//    public boolean getNEventsByIDs() throws IOException{
//       /*curl http://host_address.com/events?ids=<`ID_1`>,<`ID_2`>,...,<`ID_n`>*/
//
//        return ;
//     }
//   public boolean RSVPForAnEvent() throws IOException {
//
//         /*  curl http://localhost:5000/participation/<EVENT_ID> -X POST
//
//        Registers the current user for event_id
//        Notice - user can’t RSVP for its own event :)
//    */
//   }



//    public static void main(String[] args) throws IOException, JSONException {
//        Client client = new Client();
//        client.createEvent(new String[]{"waiting"}, "title", "desc", "", "", 0, "מרכז");
//    }
}
