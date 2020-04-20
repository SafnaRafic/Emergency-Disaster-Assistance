package org.SafnaRafic.emergencydisasterhelpline.models.data;

import org.SafnaRafic.emergencydisasterhelpline.models.BloodDonor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodDonorRepository extends PagingAndSortingRepository<BloodDonor,Integer> {
}
