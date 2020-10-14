package org.aplas.basicappx;

public class Temperature {
    private double celcius;

    public Temperature() {
        this.celcius = 0;
    }

    public void setCelcius(double c) {
        celcius = c;
    }

    public void setFahrenheit(double c) {
        celcius = (c-32)/9*5;
    }

    public void setKelvins(double c) {
        celcius = c - 273.15;
    }

    public double getCelcius() {
        return celcius;
    }

    public double getFahrenheit() {
        return celcius * 9/5 + 32;
    }

    public double getKelvins() {
        return celcius + 273.15;
    }
    public double convert(String oriUnit, String convUnit, double value) {
        // C to C
        if(oriUnit.equals("°C") && convUnit.equals("°C")) {
            setCelcius(value);
            return getCelcius();
        }
        // C to F
        else if(oriUnit.equals("°C") && convUnit.equals("°F")) {
            setCelcius(value);
            return getFahrenheit();
        }
        // C to K
        else if(oriUnit.equals("°C") && convUnit.equals("K")) {
            setCelcius(value);
            return getKelvins();
        }
        // F to C
        else if(oriUnit.equals("°F") && convUnit.equals("°C")) {
            setFahrenheit(value);
            return getCelcius();
        }
        // F to F
        else if(oriUnit.equals("°F") && convUnit.equals("°F")) {
            setFahrenheit(value);
            return getFahrenheit();
        }
        // F to K
        else if(oriUnit.equals("°F") && convUnit.equals("K")) {
            setFahrenheit(value);
            return getKelvins();
        }
        // K to C
        else if(oriUnit.equals("K") && convUnit.equals("°C")) {
            setKelvins(value);
            return getCelcius();
        }
        // K to F
        else if(oriUnit.equals("K") && convUnit.equals("°F")) {
            setKelvins(value);
            return getFahrenheit();
        }
        // K to K
        else {
            setKelvins(value);
            return getKelvins();
        }
    }
}
