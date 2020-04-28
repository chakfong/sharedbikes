package com.fly.rental.dao;

import com.fly.rental.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackDao extends JpaRepository<Track, Long> {

}
