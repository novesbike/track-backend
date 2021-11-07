package com.hexagonal.api.application.configs;

import com.hexagonal.api.application.adapters.persistence.jpa.ActivityJpaRepository;
import com.hexagonal.api.application.adapters.persistence.jpa.RoleJpaRepository;
import com.hexagonal.api.application.adapters.persistence.jpa.UserJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.ActivityModel;
import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.application.adapters.persistence.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Profile({"dev", "test"})
@RequiredArgsConstructor
public class Seeds implements ApplicationListener<ContextRefreshedEvent> {

  private RoleJpaRepository repository;
  private UserJpaRepository userJpaRepository;
  private ActivityJpaRepository activityJpaRepository;
  private BCryptPasswordEncoder bCrypt;

  @Autowired
  public Seeds(RoleJpaRepository repository, UserJpaRepository userJpaRepository, ActivityJpaRepository activityJpaRepository, BCryptPasswordEncoder bCrypt) {
    this.repository = repository;
    this.userJpaRepository = userJpaRepository;
    this.activityJpaRepository = activityJpaRepository;
    this.bCrypt = bCrypt;
  }

  public static String getRandomTitle() {
    var title = "Eu pedalei pela " + generateRandomIntIntRange(1, 1000) + " vez!";
    return title;
  }

  public static float getRandomDouble() {
    float x = (float) ((Math.random() * ((100 - 2) + 1)) + 2);
    return x;
  }

  public static int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  public static LocalDate getRandomDate() {
    var year = generateRandomIntIntRange(2020, 2021);
    var month = generateRandomIntIntRange(1, 10);
    var days = generateRandomIntIntRange(1, 28);
    return LocalDate.of(year, month, days);
  }

  public static LocalTime getRandomTiming() {
    var hour = generateRandomIntIntRange(0, 23);
    var minute = generateRandomIntIntRange(0, 59);
    var second = generateRandomIntIntRange(0, 59);
    return LocalTime.of(hour, minute, second);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    var roles = repository.findAll();

    if (roles.isEmpty()) {
      var roles_list = repository.saveAll(List.of(
              new RoleModel("ROLE_USER", "Usuário"),
              new RoleModel("ROLE_ADMIN", "Administrador")
      ));

      runSeeds(roles_list.get(0), roles_list);
    }

  }

  private void runSeeds(RoleModel role, List<RoleModel> roles) {

    var admin = new UserModel("Admin", "admin@admin.com", bCrypt.encode("admin"), "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", roles);

    var user1 = new UserModel("Maria", "a_vanessinha_1990@hotmail.com", "tJ=n8;M~68K;?X!W", "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", List.of(role));
    var user2 = new UserModel("Jorge", "aaanika2@hotmail.com", "^e?BXh5MK-:!tB/", "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", List.of(role));
    var user3 = new UserModel("Francisco", "adamyth@gmail.com", "Jrd},3Qpd68'S/g[", "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", List.of(role));
    var user4 = new UserModel("Edson", "adilson.mariano5@terra.com.br", "2#E9Az)Ytpk57^w", "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", List.of(role));
    var user5 = new UserModel("Luciana", "adriano_wolf1@hotmail.com", "2#E9Az)Ytpk57^w", "https://pixabay.com/pt/photos/erro-n%c3%a3o-encontrado-lego-n%c3%bamero-2129569/", List.of(role));

    userJpaRepository.saveAll(List.of(user1, user2, user3, user4, user5, admin));

    var activity_user1 = new ActivityModel(user1.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity_user2 = new ActivityModel(user1.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity_user3 = new ActivityModel(user1.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity_user4 = new ActivityModel(user1.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity_user5 = new ActivityModel(user1.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());

    //user2
    var activity2_user1 = new ActivityModel(user2.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity2_user2 = new ActivityModel(user2.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity2_user3 = new ActivityModel(user2.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity2_user4 = new ActivityModel(user2.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity2_user5 = new ActivityModel(user2.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());

    //user3
    var activity3_user1 = new ActivityModel(user3.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity3_user2 = new ActivityModel(user3.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity3_user3 = new ActivityModel(user3.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity3_user4 = new ActivityModel(user3.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity3_user5 = new ActivityModel(user3.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());

    //user4
    var activity4_user1 = new ActivityModel(user4.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity4_user2 = new ActivityModel(user4.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity4_user3 = new ActivityModel(user4.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity4_user4 = new ActivityModel(user4.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity4_user5 = new ActivityModel(user4.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());

    //user5
    var activity5_user1 = new ActivityModel(user5.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity5_user2 = new ActivityModel(user5.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity5_user3 = new ActivityModel(user5.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity5_user4 = new ActivityModel(user5.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());
    var activity5_user5 = new ActivityModel(user5.getId(), getRandomTitle(), "it's is a description", getRandomDate(), getRandomTiming(), getRandomDouble(), getRandomDouble(), getRandomDouble());

    activityJpaRepository.saveAll(List.of(
            activity_user1, activity_user2, activity_user3, activity_user4, activity_user5,
            activity2_user1, activity2_user2, activity2_user3, activity2_user4, activity2_user5,
            activity3_user1, activity3_user2, activity3_user3, activity3_user4, activity3_user5,
            activity4_user1, activity4_user2, activity4_user3, activity4_user4, activity4_user5,
            activity5_user1, activity5_user2, activity5_user3, activity5_user4, activity5_user5
    ));
  }

}
