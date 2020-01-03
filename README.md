# Care-Direction

careDirection_Android파트 git 장소입니다.


![workflow_product](https://user-images.githubusercontent.com/48307960/71580292-5493d880-2b43-11ea-85c1-4cdc682be3f7.png)



# 📚 주요 라이브러리

📕 MpAndroid Chart

```
implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
```

그래프를 만들어 주는 라이브러리다. 선 그래프, 막대 그래프, 원 그래프, 분산형 차트, 캔들 스틱 차트, 버블 차트, 레이더 차트 등이 있다.

이 프로젝트에서는 **BarChart** 를 사용하였다.

[참고 자료(github)](https://github.com/PhilJay/MPAndroidChart)

📕Google Material

```
implementation 'com.google.android.material:material:1.0.0'
```

주로 cardview, recyclerview 를 커스텀하기 위해 사용한다. 또한 버튼 탭이나 스크롤 탭, 스크롤 시 상단 탭이 없어지는 AppBar를 구현할 수 있다.

이 프로젝트에서는 Bottom Navigation를 사용하기 위해 라이브러리는 이용하였다.

[참고자료(github)](https://github.com/material-components/material-components-android)



📕CircleImageView

```
implementation 'de.hdodenhof:circleimageview:3.0.0'
```

 imageView 를 동그랗게 커스텀하기 위해 사용한다. 

이 프로젝트에서도 image view를 원형으로 처리하기 위해서 라이브러리를 이용하였다.

[참고자료(github)](https://github.com/hdodenhof/CircleImageView)



📕Glide

```
 implementation 'com.github.bumptech.glide:glide:4.10.0'
 annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
```

Glide는 비디오 스틸, 이미지 및 애니메이션 GIF 가져 오기, 디코딩 및 표시를 지원한다.

기본적으로 Glide는 사용자 정의 `HttpUrlConnection`기반 스택을 사용하여 이미지를 가져오기 용이하다.

 이 프로젝트에서는 server에서 `HttpUrlConnection`기반으로 이미지를 주고 받기 위하여 사용하였다.

[참고자료(github)](https://github.com/bumptech/glide)



📕DialogPlus

```
    implementation 'com.orhanobut:dialogplus:1.11@aar'
```

주로 dialog의 animation 효과를 손쉽게 쓰기 위해서 사용한다.

이 프로젝트에서는 top dialog를 쓰기 위해서 이용하였다.

[참고자료(github)](https://github.com/orhanobut/dialogplus)



📕Retrofit  

```
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
```

서버와 HTTP 규약을 통해 데이터를 주고 받을 수 있는 라이브러리 



POST,GET,PUT,DELETE 통신 매서드를 이용하여 

(request) 서버에게 JSON형식으로 데이터를 주고

(response) 서버 DB의 데이터를 가져올 수 있다.

이 프로젝트에서는 JSON 데이터를 이용한 rest 서버 통신을 위해 call 객체를 사용하였다.

[참고자료(공식 홈페이지)](https://square.github.io/retrofit/)





## 핵심기능

* ### 설문조사

* ### 설문조사에 따른 그래프 

* ### 제품 기준 확인 및 검색

* ### 성분학습  

  <br><br>

# Naming Rule

* ## View id값

  * 버튼 : btn_
  * 텍스트뷰 : txt_~
  * 이미지뷰 : img_~
  * 에디트텍스트 : edit
  * RecyclerView : rv_~_
  * RecyclerView item.xml : rv_item_feature.xml
  * View in RecyclerView item.xml: view_rv_item_feature_component <br> ex)txt_rv_item_shopping_product_name

  

* ## Border 

  * ### radius

    기본 틀 : color_border_form

    * 6dp  -> color_border_square / gray_boder_lineㅜ
    * 10dp -> color_border_long
    * 18dp -> white_border_fill / white_border_line
    * 23dp -> color_border

* ## Package 분류

  * home  
    필수 비타민&미네랄 -권장 섭취량과 상한 섭취량에 대한 그래프를 보여줌  
    기능성 원료- 케어받는 기능 표시  
    복용관리 – 복용하고 있는 제품에 대한 관리<br>
    * functional : 기능성 원료 - 케어받는 기능 표시 
  * product  
    영양제 제품에 대한 정보와 해당 제품에 대한 기준을 제시하여, 제품을 보고 자신에게 맞는 영양제를 직관적으로 알 수 있는 페이지.  
    * Detail : 제품 상세페이지에 대한 모든 정보가 들어있는 코드   
    * Search : Fragment 제품 탭으로, 제품 검색과 제품 기준 뷰로 넘어갈 수 있는 코드  
    * Standard : 제품에 성분 대한 정보를 직관적으로 알 수 있게 제품의 정보를 기준으로 한눈에 볼 수 있는 뷰

  * research  
    사용자의 정보(userInfo)와 유형(lifestyle)을 파악하기 위한 설문조사 기능 구현
    * DB : 설문조사 '이어하기'를 위한 내부저장소 및 해당 액티비티로의 이동과 렌더링을 관리
    * lifestyle : 사용자 유형에 대한 설문조사를 진행(케디만의 생활패턴 관련 질문)
    * userInfo : 사용자 정보에 대한 설문조사를 진행(일반적인 사용자 정보 관련 질문)

* ## Git 사용시 주의사항

  * master 브랜치에 올리기 전에 deve 브랜치에서 먼저 합치기

  

  
