package me.kingcjy.simple.memo.memo;

import lombok.RequiredArgsConstructor;
import me.kingcjy.simple.memo.domain.User;
import me.kingcjy.simple.memo.domain.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public List<MemoDto.MemoResponseDto> getMemos() {
        return memoRepository.findAll().stream().map(MemoDto.MemoResponseDto::new).collect(Collectors.toList());
    }

    public Long addMemo(MemoDto.MemoRequestDto dto) {
        String email = ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttribute("uid");

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent() == false) {
            throw new RuntimeException("User not Found");
        }

        Memo memo = new Memo(userOptional.get(), dto.getContents());
        memoRepository.save(memo);
        return memo.getId();
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }
}
