package com.mytutor.server.Repository;

import com.mytutor.server.Models.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, String> {
}
