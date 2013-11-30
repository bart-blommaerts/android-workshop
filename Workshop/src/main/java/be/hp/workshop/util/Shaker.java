package be.hp.workshop.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;

/**
 * Created by bart on 11/30/13.
 */
public class Shaker {
    private SensorManager sensorManager = null;
    private long lastShakeTimestamp = 0;
    private double threshold = 1.0d;
    private long gap = 0;
    private Shaker.Callbacks cb = null;

    public Shaker(Context context, double threshold, long gap, Shaker.Callbacks cb) {
        this.threshold = threshold * threshold;
        this.threshold = this.threshold * SensorManager.GRAVITY_EARTH
                * SensorManager.GRAVITY_EARTH;
        this.gap = gap;
        this.cb = cb;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(listener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    public void close() {
        sensorManager.unregisterListener(listener);
    }

    private void isShaking() {
        long now = SystemClock.uptimeMillis();
        try {
            if (lastShakeTimestamp == 0) {
                lastShakeTimestamp = now;

                if (cb != null) {
                    cb.shakingStarted();
                }
            } else {
                lastShakeTimestamp = now;
            }
        } catch (NullPointerException e) {

        }
    }

    private void isNotShaking() {
        long now = SystemClock.uptimeMillis();

        if (lastShakeTimestamp > 0) {
            if (now - lastShakeTimestamp > gap) {
                lastShakeTimestamp = 0;

                if (cb != null) {
                    cb.shakingStopped();
                }
            }
        }
    }

    public interface Callbacks {
        void shakingStarted();

        void shakingStopped();
    }

    private final SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent e) {
            if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                double netForce = e.values[0] * e.values[0];

                netForce += e.values[1] * e.values[1];
                netForce += e.values[2] * e.values[2];

                if (threshold < netForce) {
                    isShaking();
                } else {
                    isNotShaking();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // unused
        }
    };
}
