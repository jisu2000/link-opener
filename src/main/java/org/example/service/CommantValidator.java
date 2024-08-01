package org.example.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.example.constants.AppContants;
import org.example.messages.PrintHelpMessages;
import org.example.utils.FilesToMap;
import org.example.utils.UrlVerifier;

public class CommantValidator {

    public static void commandDetector(String arr[]) throws IOException {

        if (arr == null) {
            System.out.println("Visit lo --help!");
        }

        System.out.println(Arrays.toString(arr));

        if (arr.length == 1 && arr[0].contains("--")) {

            if (arr[0].equals(AppContants.HELP_COMMAND)) {
                PrintHelpMessages.printAllOptions();
            }

            else if (arr[0].equals(AppContants.RESET_COMMAND)) {
                FileDeleter.deleteFile();
            }

            else if (arr[0].equals(AppContants.LIST_COMMAND)) {
                try {
                    HashMap<String, String> map = FilesToMap.readHashMapFromFile(AppContants.FILE_PATH);
                    map.keySet().forEach(e -> {
                        System.out.println(e + "->>" + map.get(e));
                    });
                } catch (IOException e) {
                    System.out.println("Reset using --reset");
                }

            }
        } else if (arr.length==1 && !arr[0].contains("--")) {

            String shortCut = arr[0];
            HashMap<String,String> map = FilesToMap.readHashMapFromFile(AppContants.FILE_PATH);

            if(!map.containsKey(arr[0])){
                System.out.println("shortcut not found for "+arr[0]);
            }
            else {
                LinkOpener.openLink(map.get(shortCut));

            }

        }

        else if(arr.length == 3){

            if(arr[0].equals("--create")){


                HashMap<String,String> map = FilesToMap.readHashMapFromFile(AppContants.FILE_PATH);
                if(map.containsKey(arr[1])){
                    System.out.println("shortcut already added on that keyword");
                    return;
                }else{

                    if(!UrlVerifier.isUrlGiving200(arr[2])){

                        throw new RuntimeException("Invalid Url");
                    }
                    else {
                        map.put(arr[1], arr[2]);
                    }
                }


                FileDeleter.deleteFile();

                FileCreater.makeEmptyFile();

                FilesToMap.writeHashMapToFile(map,AppContants.FILE_PATH);

                System.out.println("Shortcut added");
            }



            if(arr[0].equals("--update")){


                HashMap<String,String> map = FilesToMap.readHashMapFromFile(AppContants.FILE_PATH);
                if(!map.containsKey(arr[1])){
                    System.out.println("shortcut not found");
                    return;
                }else{

                    if(!UrlVerifier.isUrlGiving200(arr[2])){

                        throw new RuntimeException("Invalid Url");
                    }
                    else {
                        map.put(arr[1], arr[2]);
                    }
                }


                FileDeleter.deleteFile();

                FileCreater.makeEmptyFile();

                FilesToMap.writeHashMapToFile(map,AppContants.FILE_PATH);

                System.out.println("Shortcut Updated");
            }
        }
        else if(arr.length ==2 ){
            if(arr[0].equals("--delete")){
                HashMap<String, String> map = FilesToMap.readHashMapFromFile(AppContants.FILE_PATH);

                if(map.containsKey(arr[1])){
                    map.remove(arr[1]);


                    FileDeleter.deleteFile();

                    FileCreater.makeEmptyFile();
    
                    FilesToMap.writeHashMapToFile(map,AppContants.FILE_PATH);
                    System.out.println("Successfully deleted "+arr[1]);
            
                }
                else{
                    System.out.println("Shortcut Not present");
                }

            }
        }
    }

}
