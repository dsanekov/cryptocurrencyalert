package com.cryptocurrencyalert.repisitories;

import com.cryptocurrencyalert.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person,Integer> {
}
