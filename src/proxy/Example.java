package proxy;

import proxy.Data.*;

import java.util.Arrays;
import java.util.List;

import static lib.Printer.println;

public class Example {

    public static void run() {
        testYoutubeWithProxyAndCache();
    }

    private static void testYoutubeWithProxyAndCache() {
        ThirdPartyYouTubeLib thirdPartyYouTube = new ThirdPartyYouTube();
        ThirdPartyYouTubeLib youTubeDownloaderWithCache = new ThirdPartyYouTubeWithCache(thirdPartyYouTube);

        List<String> bannedVideos = Arrays.asList("someothervid", "3sdfgsd1j333");
        ThirdPartyYouTubeLib youTubeDownloaderWithProxy = new ThirdPartyYouTubeWithProxy(bannedVideos, youTubeDownloaderWithCache);

        YouTubeDownloader youTubeDownloader = new YouTubeDownloader(youTubeDownloaderWithProxy);
        testPerformance(youTubeDownloader);
    }

    private static void testYoutubeWithCache() {
        YouTubeDownloader naiveDownloader = new YouTubeDownloader(new ThirdPartyYouTube());
        YouTubeDownloader smartDownloader = new YouTubeDownloader(new ThirdPartyYouTubeWithCache());

        long naive = testPerformance(naiveDownloader);
        long smart = testPerformance(smartDownloader);
        println("Time saved by caching proxy: " + (naive - smart) + "ms");
    }

    private static long testPerformance(YouTubeDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");

        // Users might visit the same page quite often.
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        println("Time elapsed: " + estimatedTime + "ms\n");

        return estimatedTime;
    }
}
