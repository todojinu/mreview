package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

/*
이미지 정보를 기록할 Entity 클래스
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
// 지연로딩시 연관 객체 출력으로 인한 무한참조 발생을 막기 위해 exclude 속성 추가
// -> toString()으로 출력시 movie는 제외
@ToString(exclude = "movie")
public class MovieImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    // @XXXToOne은 기본이 즉시 로딩이기때문에 LAZY로 설정
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

}
