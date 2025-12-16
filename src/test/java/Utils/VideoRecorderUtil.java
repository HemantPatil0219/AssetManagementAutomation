package Utils;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorderUtil {

    private static final ThreadLocal<ScreenRecorder> recorder = new ThreadLocal<>();
    private static final ThreadLocal<String> videoBaseName = new ThreadLocal<>();

    public static void startRecording(String testNameWithBrowser) throws Exception {

        File videoDir = new File("test-output/videos");
        if (!videoDir.exists()) {
            videoDir.mkdirs();
        }

        // unique name (test + browser + timestamp)
        String name = testNameWithBrowser + "_" + System.currentTimeMillis();
        videoBaseName.set(name);

        GraphicsConfiguration gc =
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice()
                        .getDefaultConfiguration();

        ScreenRecorder screenRecorder = new ScreenRecorder(
                gc,
                new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24,
                        FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null,
                videoDir
        ) {
            @Override
            protected File createMovieFile(Format fileFormat) throws IOException {
                return new File(videoDir, videoBaseName.get() + ".avi");
            }
        };

        recorder.set(screenRecorder);
        screenRecorder.start();
    }

    /** AVI path (created during execution) */
    public static String getAviPath() {
        return "test-output/videos/" + videoBaseName.get() + ".avi";
    }

    /** MP4 path (after FFmpeg conversion) */
    public static String getMp4Path() {
        return "test-output/videos/" + videoBaseName.get() + ".mp4";
    }

    public static void stopRecording() throws Exception {
        if (recorder.get() != null) {
            recorder.get().stop();
            recorder.remove();
            // DO NOT remove videoBaseName here (needed in @AfterMethod)
        }
    }

    /** call after report logging is done */
    public static void clear() {
        videoBaseName.remove();
    }
}
