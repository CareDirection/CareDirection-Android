# Care-Direction
careDirection_Android파트 git 장소입니다.

Naming Rule
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
		* 6dp  -> color_border_square / gray_boder_line
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
	
	
	
	
