package com.appSecure.codefellowship.Model;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;


import javax.persistence.*;

    @Setter
    @Getter
    @Entity
    public class Role {

        @Setter(value= AccessLevel.NONE)
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id", nullable = false)
        private int id ;

        String name ;

        public Role(String name) {
            this.name = name;
        }

        public Role() {
        }
}
