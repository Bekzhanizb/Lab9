package bekezhan.io.lab9.repository;

import bekezhan.io.lab9.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCountryId(Long countryId);
}
