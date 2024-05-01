package AgenciaTurismo.pruebaTecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



        @Bean
        public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf().disable()//csrf is disabled for simplicity
                    .authorizeHttpRequests()
                        .requestMatchers("/agency/hotels/new").permitAll()
                        .requestMatchers("/agency/hotels/delete/{id}").permitAll()
                        .requestMatchers("/agency/hotels/edit/{id}").permitAll()
                        .requestMatchers("/agency/hotels/rooms/new").permitAll()
                        .requestMatchers("/agency/hotels/rooms/delete/{id}").permitAll()
                        .requestMatchers("/agency/hotels/rooms/edit/{id}").permitAll()
                        .requestMatchers("/agency/room-booking/new").permitAll()
                        .requestMatchers("/agency/room-booking/delete/{id}").permitAll()
                        .requestMatchers("/agency/room-booking/edit/{id}").permitAll()
                        .requestMatchers("/agency/flights/new").permitAll()
                        .requestMatchers("/agency/flights/delete/{id}").permitAll()
                        .requestMatchers("/agency/flights/edit/{id}").permitAll()
                        .requestMatchers("/agency/flights/byDatesAndCities").permitAll()
                        .requestMatchers("/agency//flight-booking/new").permitAll()
                        .requestMatchers("/agency/flight-booking/list").permitAll()
                        .anyRequest().authenticated()
                    .and()
                    .formLogin().permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .build();
        }
}
