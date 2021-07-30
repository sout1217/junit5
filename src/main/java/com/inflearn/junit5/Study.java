package com.inflearn.junit5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final StudyStatus status = StudyStatus.DRAFT;
    private int limit;
    private String name;

    public Study() {
    }

    public Study(int limit) {

        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야 한다");
        }

        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }

    public void setOwner(Member member) {
    }
}
