//package me.kingcjy.simple.authorization.config;
//
//import lombok.RequiredArgsConstructor;
//import me.kingcjy.simple.authorization.domain.User;
//import me.kingcjy.simple.authorization.domain.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.sql.DataSource;
//import java.util.Collections;
//
//@Component
//@RequiredArgsConstructor
//
//public class InitConfig {
//
//    private final DataSource dataSource;
//
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//            jdbcTemplate.execute("create table IF NOT EXISTS oauth_client_token (\n" +
//                    "  token_id VARCHAR(256),\n" +
//                    "  token LONGVARBINARY,\n" +
//                    "  authentication_id VARCHAR(256) PRIMARY KEY,\n" +
//                    "  user_name VARCHAR(256),\n" +
//                    "  client_id VARCHAR(256)\n" +
//                    ");\n" +
//                    " \n" +
//                    "create table IF NOT EXISTS oauth_access_token (\n" +
//                    "  token_id VARCHAR(256),\n" +
//                    "  token LONGVARBINARY,\n" +
//                    "  authentication_id VARCHAR(256) PRIMARY KEY,\n" +
//                    "  user_name VARCHAR(256),\n" +
//                    "  client_id VARCHAR(256),\n" +
//                    "  authentication LONGVARBINARY,\n" +
//                    "  refresh_token VARCHAR(256)\n" +
//                    ");\n" +
//                    " \n" +
//                    "create table IF NOT EXISTS oauth_refresh_token (\n" +
//                    "  token_id VARCHAR(256),\n" +
//                    "  token LONGVARBINARY,\n" +
//                    "  authentication LONGVARBINARY\n" +
//                    ");\n" +
//                    " \n" +
//                    "create table IF NOT EXISTS oauth_code (\n" +
//                    "  code VARCHAR(256), authentication LONGVARBINARY\n" +
//                    ");\n" +
//                    " \n" +
//                    "create table IF NOT EXISTS oauth_approvals (\n" +
//                    "    userId VARCHAR(256),\n" +
//                    "    clientId VARCHAR(256),\n" +
//                    "    scope VARCHAR(256),\n" +
//                    "    status VARCHAR(10),\n" +
//                    "    expiresAt TIMESTAMP,\n" +
//                    "    lastModifiedAt TIMESTAMP\n" +
//                    ");");
//
//            jdbcTemplate.execute("create table IF NOT EXISTS oauth_client_details (\n" +
//                    "  client_id VARCHAR(256) PRIMARY KEY,\n" +
//                    "  resource_ids VARCHAR(256),\n" +
//                    "  client_secret VARCHAR(256),\n" +
//                    "  scope VARCHAR(256),\n" +
//                    "  authorized_grant_types VARCHAR(256),\n" +
//                    "  web_server_redirect_uri VARCHAR(256),\n" +
//                    "  authorities VARCHAR(256),\n" +
//                    "  access_token_validity INTEGER,\n" +
//                    "  refresh_token_validity INTEGER,\n" +
//                    "  additional_information VARCHAR(4096),\n" +
//                    "  autoapprove VARCHAR(256)\n" +
//                    ");");
//
//            jdbcTemplate.execute("insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)\n" +
//                    "values('testClientId',null,'testSecret','read,write','authorization_code,refresh_token','http://localhost:8081/oauth/callback','ROLE_USER',36000,50000,null,null);");
//
//            jdbcTemplate.execute("insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)\n" +
//                    "values('simpleUser',null,'simpleUserSecret','read,write','authorization_code,refresh_token','http://localhost:8081/oauth/callback','ROLE_USER',36000,50000,null,null);");
//
//            userRepository.save(User.builder()
//                    .uid("tlssycks@gmail.com")
//                    .password(passwordEncoder.encode("1234"))
//                    .name("KingCjy")
//                    .roles(Collections.singletonList("ROLE_USER"))
//                    .build());
//
//        };
//    }
//}
