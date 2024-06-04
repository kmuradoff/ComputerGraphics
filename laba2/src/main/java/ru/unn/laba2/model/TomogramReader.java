package ru.unn.laba2.model;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TomogramReader {
    private int width;
    private int height;
    private int depth;
    private short[] data;

    public void read(String fileName) throws IOException {
        // Чтение и вывод первых 32 байтов файла для анализа
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            byte[] header = new byte[32];
            dis.readFully(header);
            System.out.println("File header (first 32 bytes):");
            for (byte b : header) {
                System.out.printf("%02x ", b);
            }
            System.out.println();
        }

        // Перемотка назад и чтение заголовка с учетом порядка байтов (Little Endian)
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            width = readIntLE(dis);
            height = readIntLE(dis);
            depth = readIntLE(dis);
            System.out.println("Tomogram dimensions: width=" + width + ", height=" + height + ", depth=" + depth);

            if (width <= 0 || height <= 0 || depth <= 0) {
                throw new IOException("Invalid tomogram dimensions");
            }

            int dataSize = width * height * depth;
            data = new short[dataSize];
            for (int i = 0; i < dataSize; i++) {
                data[i] = readShortLE(dis);
            }
            System.out.println("Tomogram data loaded. Total voxels: " + dataSize);
        }
    }

    private int readIntLE(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[4];
        dis.readFully(bytes);
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private short readShortLE(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[2];
        dis.readFully(bytes);
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public short getVoxel(int x, int y, int z) {
        int index = z * width * height + y * width + x;
        return data[index];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
