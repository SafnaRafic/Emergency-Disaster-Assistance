package org.SafnaRafic.emergencydisasterhelpline.models.data;

import org.SafnaRafic.emergencydisasterhelpline.models.Inneed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InneedRepository extends PagingAndSortingRepository<Inneed,Integer> {
}
