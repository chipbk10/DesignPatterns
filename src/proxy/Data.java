package proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lib.Printer.println;
import static proxy.DummyData.*;

public class Data {

    public static class Video {
        private String id, title, data;
        public Video(String id, String title) {
            this.id = id;
            this.title = title;
            this.data = "Random video.";
        }
    }

    public interface ThirdPartyYouTubeLib {
        Map<String, Video> getPopularVideos();
        Video getVideo(String videoId);
    }

    public static class ThirdPartyYouTube implements ThirdPartyYouTubeLib {

        DummyNetworkLatency network = DummyNetworkLatency.sharedInstance;

        @Override
        public Map<String, Video> getPopularVideos() {

            network.connectToServerWithNetworkLatency("www.youtube.com");

            Map<String, Video> popularVideos = network.downloadPopularVideosWithNetworkLatency();

            return popularVideos;
        }

        @Override
        public Video getVideo(String videoId) {

            network.connectToServerWithNetworkLatency("www.youtube.com/" + videoId);

            Video video = network.downloadVideoWithNetworkLatency(videoId);

            return video;
        }
    }

    public static class ThirdPartyYouTubeWithCache implements ThirdPartyYouTubeLib {

        private ThirdPartyYouTubeLib youtubeService;
        private Map<String, Video> cachePopular = new HashMap<>();
        private Map<String, Video> cacheAll = new HashMap<>();

        public ThirdPartyYouTubeWithCache() {
            this.youtubeService = new ThirdPartyYouTube();
        }

        public ThirdPartyYouTubeWithCache(ThirdPartyYouTubeLib youtubeService) {
            this.youtubeService = youtubeService;
        }

        @Override
        public Map<String, Video> getPopularVideos() {
            if (cachePopular.isEmpty()) {
                cachePopular = youtubeService.getPopularVideos();
            }
            else {
                println("Retrieved list from cache.");
            }
            return cachePopular;
        }

        @Override
        public Video getVideo(String videoId) {
            Video video = cacheAll.get(videoId);
            if (video == null) {
                video = youtubeService.getVideo(videoId);
                cacheAll.put(videoId, video);
            } else {
                System.out.println("Retrieved video '" + videoId + "' from cache.");
            }
            return video;
        }

        public void clearCache() {
            cachePopular.clear();
            cacheAll.clear();
        }
    }

    public static class ThirdPartyYouTubeWithProxy implements ThirdPartyYouTubeLib {

        private ThirdPartyYouTubeLib youtubeService;
        private List<String> bannedVideos;

        public ThirdPartyYouTubeWithProxy(List<String> bannedVideos, ThirdPartyYouTubeLib youtubeService) {
            this.bannedVideos = bannedVideos;
            this.youtubeService = youtubeService;
        }

        @Override
        public Map<String, Video> getPopularVideos() {
            return youtubeService.getPopularVideos();
        }

        @Override
        public Video getVideo(String videoId) {
            if (bannedVideos != null && bannedVideos.contains(videoId)) {
                println("\n" + videoId + " is banned. Cannot download!");
                return null;
            }
            else {
                return youtubeService.getVideo(videoId);
            }
        }
    }

    public static class YouTubeDownloader {
        private ThirdPartyYouTubeLib api;

        public YouTubeDownloader(ThirdPartyYouTubeLib api) {
            this.api = api;
        }

        public void renderVideoPage(String videoId) {
            Video video = api.getVideo(videoId);
            if (video != null) {
                println("\n-------------------------------");
                println("Video page (imagine fancy HTML)");
                println("ID: " + video.id);
                println("Title: " + video.title);
                println("Video: " + video.data);
                println("-------------------------------\n");
            }
        }

        public void renderPopularVideos() {
            Map<String, Video> list = api.getPopularVideos();
            println("\n-------------------------------");
            println("Most popular videos on YouTube (imagine fancy HTML)");
            for (Video video : list.values()) {
                println("ID: " + video.id + " / Title: " + video.title);
            }
            println("-------------------------------\n");
        }
    }
}
