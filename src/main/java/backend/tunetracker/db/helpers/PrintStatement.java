package backend.tunetracker.db.helpers;

import java.util.List;

public class PrintStatement {
    public static void printPlaylistHeaderFooter(List<String> playlistNames, String username) {
        username = (username != "") ? username : "Your ";
        String header = "================" + username + " playlists================";

        int lengthOfHeader = 0;
        for (int i = 0; i < header.length(); i++) {
            lengthOfHeader++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lengthOfHeader; i++) {
            sb.append("=");
        }

        System.out.println(header);
        for (int i = 0; i < playlistNames.size(); i++) {
            System.out.println(playlistNames.get(i));
        }
        System.out.println(sb.toString());
    }

    public static void printSongsFromPlaylist(String playlist, List<String> songs) {
//        username = (username != "") ? username : "Your ";
        String header = "================" + playlist + " songs================";

        int lengthOfHeader = 0;
        for (int i = 0; i < header.length(); i++) {
            lengthOfHeader++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lengthOfHeader; i++) {
            sb.append("=");
        }

        System.out.println(header);
        for (int i = 0; i < songs.size(); i++) {
            System.out.println(songs.get(i));
        }
        System.out.println(sb.toString());
    }

}
