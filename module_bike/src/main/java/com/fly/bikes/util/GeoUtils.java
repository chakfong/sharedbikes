package com.fly.bikes.util;

import org.reflections.vfs.Vfs;

import java.util.Arrays;

public class GeoUtils {
    private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int[] BITS = {16, 8, 4, 2, 1};
    private static final int[] DIRECTION = {-1, 0, 1};
    private static final int[] BASE_32_IDX;

    static {
        BASE_32_IDX = new int[BASE_32[BASE_32.length - 1] - BASE_32[0] + 1];
        assert BASE_32_IDX.length < 100;//reasonable length
        Arrays.fill(BASE_32_IDX, -500);
        for (int i = 0; i < BASE_32.length; i++) {
            BASE_32_IDX[BASE_32[i] - BASE_32[0]] = i;
        }
    }

    public static void main(String[] args) {
        String[] f = GeoUtils.suroundingLocation("ws0eup");
        Arrays.stream(f).forEach(System.out::println);
    }

    public static String[] suroundingLocation(String geoHash) {
        char[] bit = geoHash.toCharArray();
        int lat = 0, lng = 0;
        boolean isEven = true;
        String[] result = new String[9];
        for (int i = 0; i < geoHash.length(); i++) {
            char c = geoHash.charAt(i);
            if (c >= 'A' && c <= 'Z')
                c -= ('A' - 'a');
            final int cd = BASE_32_IDX[c - BASE_32[0]];
            for (int mask : BITS) {
                if (isEven) {
                    if ((cd & mask) != 0) {
                        lng = (lng << 1) + 1;
                    } else {
                        lng = lng << 1;
                    }
                } else {
                    if ((cd & mask) != 0) {
                        lat = (lat << 1) + 1;
                    } else {
                        lat = lat << 1;
                    }
                }
                isEven = !isEven;
            }
        }
        int k = 0;
        System.out.println(Integer.toBinaryString(lng) + " " + Integer.toBinaryString(lat));
        for (int i = 0; i < DIRECTION.length; i++) {
            for (int j = 0; j < DIRECTION.length; j++) {
                result[k++] = toGeohash(lat + DIRECTION[i], lng + DIRECTION[j], geoHash.length());
            }
        }
        return result;
    }

    public static String toGeohash(int lat, int lng, int length) {
        final StringBuilder geohash = new StringBuilder();
        boolean isEven = (length & 1) == 1;
        while (lat != 0 || lng != 0) {
            int ch;
            if (isEven) {
                ch = ((lng & 4) << 2) + ((lat & 2) << 2) + ((lng & 2) << 1) + ((lat & 1) << 1) + (lng & 1);
                lat = (lat >> 2);
                lng = (lng >> 3);
            } else {
                ch = ((lat & 4) << 2) + ((lng & 2) << 2) + ((lat & 2) << 1) + ((lng & 1) << 1) + (lat & 1);
                lat = (lat >> 3);
                lng = (lng >> 2);
            }
            isEven = !isEven;
            geohash.append(BASE_32[ch]);
        }
        geohash.reverse();
        return geohash.toString();
    }

    public static String encodingLocation(Double lat, Double lng, int precision) {
        double[] latInterval = {-90.0, 90.0};
        double[] lngInterval = {-180.0, 180.0};

        final StringBuilder geohash = new StringBuilder(precision);
        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while (geohash.length() < precision) {
            double mid = 0.0;
            if (isEven) {
                mid = (lngInterval[0] + lngInterval[1]) / 2D;
                if (lng > mid) {
                    ch |= BITS[bit];
                    lngInterval[0] = mid;
                } else {
                    lngInterval[1] = mid;
                }
            } else {
                mid = (latInterval[0] + latInterval[1]) / 2D;
                if (lat > mid) {
                    ch |= BITS[bit];
                    latInterval[0] = mid;
                } else {
                    latInterval[1] = mid;
                }
            }

            isEven = !isEven;

            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE_32[ch]);
                bit = 0;
                ch = 0;
            }
        }

        return geohash.toString();
    }


}
