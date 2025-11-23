package org.teiacoltec.poo.tp4.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupManager {
    private static final String DB_PATH = "SistemaNotasEscolar/lib/notas.db";
    private static final String BKP_DIR = "SistemaNotasEscolar/lib/backups";

    public static File backup() throws IOException {
        File src = new File(DB_PATH);
        File dir = new File(BKP_DIR);
        if (!dir.exists()) dir.mkdirs();
        String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File dst = new File(dir, "notas-" + stamp + ".db");
        copy(src, dst);
        return dst;
    }

    public static File restoreLatest() throws IOException {
        File dir = new File(BKP_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("notas-") && name.endsWith(".db"));
        if (files == null || files.length == 0) return null;
        File latest = files[0];
        for (File f : files) { if (f.lastModified() > latest.lastModified()) latest = f; }
        File dst = new File(DB_PATH);
        copy(latest, dst);
        return dst;
    }

    private static void copy(File src, File dst) throws IOException {
        if (!src.exists()) return;
        try (FileChannel in = new FileInputStream(src).getChannel();
             FileChannel out = new FileOutputStream(dst).getChannel()) {
            out.transferFrom(in, 0, in.size());
        }
    }
}
