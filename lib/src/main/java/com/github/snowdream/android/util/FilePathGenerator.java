package com.github.snowdream.android.util;

import android.content.Context;
import android.text.TextUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Decide the absolute path of the file which log will be written to.
 *
 * Created by hui.yang on 2014/11/13.
 */
public abstract class FilePathGenerator {
    private String path = null;

    /**
     * Generate the file path of the log.
     * <p/>
     * The file path should be absolute.
     *
     * @return the file path of the log
     */
    public abstract String generateFilePath();

    /**
     * Whetether to generate the file path of the log.
     *
     * @return if true,generate the file path of the log, otherwise not.
     */
    public abstract Boolean isGenerate();

    /**
     * It is time to generate the new file path of the log.
     * You can get the new and the old file path of the log.
     * <p/>
     * The file path should be absolute.
     *
     * @param newPath the new file path
     * @param oldPath the old file path
     */
    public abstract void onGenerate(String newPath, String oldPath);

    /**
     * Get the file path of the log. generate a new file path  if needed.
     *
     * @return the file path of the log.
     */
    public final String getPath() {
        if (isGenerate()) {
            String newPath = generateFilePath();

            onGenerate(newPath, path);
            path = newPath;
        }

        return path;
    }


    /**
     * Default FilePathGenerator
     *
     */
    public static class DefaultFilePathGenerator extends FilePathGenerator {
        private String dir = null;
        private File file = null;

        //Supress default constructor for noninstantiability
        private DefaultFilePathGenerator() {
            throw new AssertionError();
        }

        /**
         * dir is context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log"
         * file is app log
         *
         * @param context
         */
        DefaultFilePathGenerator(Context context) {
            if (context != null) {
                dir = context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log";
            }
        }

        /**
         * dir is from the param.
         * file is app log
         *
         * @param dir
         */
        DefaultFilePathGenerator(String dir) {
            if (dir != null) {
                this.dir = dir;
            }
        }

        @Override
        public String generateFilePath() {
            String path = null;

            if (TextUtils.isEmpty(dir)) {
                return path;
            }

            File logDir = new File(dir);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            file = new File(logDir, "app.log");

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return file.getAbsolutePath();
        }

        @Override
        public Boolean isGenerate() {
            return (file == null) || !file.exists();
        }

        @Override
        public void onGenerate(String newPath, String oldPath) {
        }
    }

    /**
     * Date FilePathGenerator
     *
     */
    public static class DateFilePathGenerator extends FilePathGenerator {
        private String dir = null;
        private File file = null;

        //Supress default constructor for noninstantiability
        private DateFilePathGenerator() {
            throw new AssertionError();
        }

        /**
         * dir is context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log"
         * file is app log
         *
         * @param context
         */
        DateFilePathGenerator(Context context) {
            if (context != null) {
                dir = context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log";
            }
        }

        /**
         * dir is from the param.
         * file is app log
         *
         * @param dir
         */
        DateFilePathGenerator(String dir) {
            if (dir != null) {
                this.dir = dir;
            }
        }

        @Override
        public String generateFilePath() {
            String path = null;

            if (TextUtils.isEmpty(dir)) {
                return path;
            }

            File logDir = new File(dir);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            Date myDate = new Date();
            FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
            String myDateString = fdf.format(myDate);

            StringBuffer buffer = new StringBuffer();
            buffer.append("app-");
            buffer.append(myDateString);
            buffer.append(".log");

            file = new File(logDir, buffer.toString());

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return file.getAbsolutePath();
        }

        @Override
        public Boolean isGenerate() {
            return (file == null) || !file.exists();
        }

        @Override
        public void onGenerate(String newPath, String oldPath) {
        }
    }

    /**
     * LimitSize FilePathGenerator
     *
     */
    public static class LimitSizeFilePathGenerator extends FilePathGenerator {
        private String dir = null;
        private File file = null;
        private int maxSize = 0;

        //Supress default constructor for noninstantiability
        private LimitSizeFilePathGenerator() {
            throw new AssertionError();
        }

        /**
         * dir is context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log"
         * file is app log
         *
         * @param context
         */
        LimitSizeFilePathGenerator(Context context, int maxSize) {
            if (context != null) {
                dir = context.getExternalFilesDir("null").getAbsolutePath() + File.pathSeparator + "snowdream" + File.pathSeparator + "log";
            }

            this.maxSize = maxSize;
        }

        /**
         * dir is from the param.
         * file is app log
         *
         * @param dir
         * @maxSize the max size of the file,with unit byte.
         */
        LimitSizeFilePathGenerator(String dir, int maxSize) {
            if (dir != null) {
                this.dir = dir;
            }

            this.maxSize = maxSize;
        }

        @Override
        public String generateFilePath() {
            String path = null;

            if (TextUtils.isEmpty(dir)) {
                return path;
            }

            File logDir = new File(dir);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            Date myDate = new Date();
            FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd-HH-mm-ss");
            String myDateString = fdf.format(myDate);

            StringBuffer buffer = new StringBuffer();
            buffer.append("app-");
            buffer.append(myDateString);
            buffer.append(".log");

            file = new File(logDir, buffer.toString());

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return file.getAbsolutePath();
        }

        @Override
        public Boolean isGenerate() {
            return (file == null) || !file.exists() || file.length() >= maxSize;
        }

        @Override
        public void onGenerate(String newPath, String oldPath) {
        }
    }
}
