package com.blapiter.atmservice;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Task implements Comparable<Task> {

    @NotNull
    @Min(1)
    @Max(9999)
    protected Integer atmId;

    @NotNull
    @Min(1)
    @Max(9999)
    protected Integer region;

    @NotNull
    protected RequestType requestType;

    Task(Integer region, Integer atmId, RequestType requestType) {
        this.region = region;
        this.atmId = atmId;
        this.requestType = requestType;
    }

    public Integer getAtmId() {
        return atmId;
    }

    public void setAtmId(Integer atmId) {
        this.atmId = atmId;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public int compareTo(Task other) {

        if (!this.region.equals(other.region)) {
            return this.region.compareTo(other.region);
        }

        if (!this.requestType.equals(other.requestType)) {
            return this.requestType.compareTo(other.requestType);
        }

        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.region, this.atmId);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        final Task other = (Task) object;

        return this.region.equals(other.region) && this.atmId.equals(other.atmId);
    }

    @Override
    public String toString() {
        return "ServiceTasks [region=" + region + ", atmId=" + atmId + ", requestType=" + requestType.toString() + "]";
    }

}