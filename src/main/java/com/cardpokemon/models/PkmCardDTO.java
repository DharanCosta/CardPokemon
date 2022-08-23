package com.cardpokemon.models;

public class PkmCardDTO {

        private Long id;
        private String nationalDex;
        private String name;

        private int total;

        private float average;

        private AttributesModel attributes;

        public PkmCardDTO() {
        }

        public PkmCardDTO(String nationalDex, String name, int total, float average, AttributesModel attributes) {
                this.nationalDex = nationalDex;
                this.name = name;
                this.total = total;
                this.average = average;
                this.attributes = attributes;
        }

        public Long getId() {
                return id;
        }

        public String getNationalDex() {
                return nationalDex;
        }

        public void setNationalDex(String nationalDex) {
                this.nationalDex = nationalDex;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public float getAverage() {
                return average;
        }

        public void setAverage(float average) {
                this.average = average;
        }

        public AttributesModel getAttributes() {
                return attributes;
        }

        public void setAttributes(AttributesModel attributes) {
                this.attributes = attributes;
        }
}
