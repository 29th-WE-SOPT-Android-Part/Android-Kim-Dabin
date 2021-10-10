| SignInActivity | SignUpActivityt  |  
|:----------|:----------:|
| <img src="https://user-images.githubusercontent.com/84564695/136655876-68f490b0-fd21-438c-aef9-3e363e9ea12e.gif" width="200" height="380"/> | <img src="https://user-images.githubusercontent.com/84564695/136656583-3aa640b4-7281-436b-bf82-274b4e2ed58e.gif" width="200" height="380"/> |
*hint속성..짤 화질이 안좋아서 잘 안보임니다 |회원가입 Text..짤 화질이 안좋아서 잘 안보임다
## ✅Level1+Level2
### ✔***SignInActivity***
 - #### 데이터 입력 확인
```kotlin
   //모든 데이터가 입력되었는지 확인
        fun isEtIdEmpty(): Boolean {
            return binding.etIdIn.text.isNullOrEmpty()
        }

        fun isEtPassword(): Boolean {
            return binding.etPassIn.text.isNullOrEmpty()
        }

        fun isAllEditTextEmpty(): Boolean {
            return isEtIdEmpty() || isEtPassword()
        }
```

   - ####  isAllEditTextEmpty에 따른 분기 처리

```kotlin
 //로그인 버튼 눌렀을때 데이터 입력에 따른 분기 이벤트
        binding.bvLogin.setOnClickListener {
            if (isAllEditTextEmpty() == true) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${binding.etIdIn.text}님 환영합니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
```
- #### 회원가입 버튼 클릭 시
```kotlin
  //회원가입 버튼시 이벤트->데이터 리턴받아와야함!
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }

        //회원가입 데이터 받아오기
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == 1) {
                val id = result.data?.getStringExtra("id")
                val pass = result.data?.getStringExtra("pass")
                binding.etIdIn.setText(id)
                binding.etPassIn.setText(pass)
            } else {
                binding.etIdIn.text.clear()
                binding.etPassIn.text.clear()
            }
        }
```
### ✔***SignUpActivity***

  - #### 데이터 입력 확인
```kotlin
   fun isEtNameEmpty(): Boolean {
            return binding.etName.text.isNullOrEmpty()
        }

        fun isEtIdEmpty(): Boolean {
            return binding.etId.text.isNullOrEmpty()
        }

        fun isEtPassword(): Boolean {
            return binding.etPass.text.isNullOrEmpty()
        }

        fun isAllEditTextEmpty(): Boolean {
            return isEtNameEmpty() || isEtIdEmpty() || isEtPassword()
        }
```
  - #### isAllEditTextEmpty에 따른 분기 처리
```kotlin
     //회원가입 완료 버튼 분기 이벤트
        binding.bvLogin.setOnClickListener {
            if (isAllEditTextEmpty() == true) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("pass", binding.etPass.text.toString())

                setResult(1, intent)
                finish() //화면이동시 intent아닌 finish로 스택에서 제거
            }
        }
```

### ✔***HomeActivity***
- #### 깃허브 페이지로 이동
```kotlin
  //깃허브 페이지로 이동
        binding.ivGithubIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dabinKim-0318"))
            startActivity(intent)
        }
```
 - 명시적 Intent:
앱 안에서 Activity나 서비스 같은 구성 요소를 시작할 때 사용하기 때문에 시작하고자 하는 액티비티 또는 서비스의 클래스 이름을 알고 있어야함
val intent2 = Intent(this@Activity_1, Activity_2::class.java)
ex)새로운 액티비티를 시작하거나 백그라운드에서 파일을 다운로드하기 위해 서비스를 시작하는 것
위에서 Activity1->Activity2 간 Intent를 전달하는 예제 역시 명시적 Intent유형이었음

 - 암시적 Intent:
특정 구성 요소의 이름을 대지 않지만, 그 대신 수행할 일반적인 작업을 선언하여 다른 앱의 구성 요소가 이를 처리할 수 있도록 할 때 사용
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
ex)사용자에게 지도에 있는 한 위치를 표시하고자 하는 경우, 암시적 인텐트를 사용하여 해당 기능을 갖춘 다른 앱이 지정된 위치를 지도에 표시하도록 요청
### ✔***그외***
- 모든 데이터가 입력되었을때만 로그인/회원가입 버튼 색깔 바뀌게하기
```kotlin
  //모든 데이터가 입력되었을때 로그인 버튼 색깔 바뀌게하기->addTextChangedListener 사용
        binding.etPassIn.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isEtIdEmpty() != true && isEtPassword() != true && p0.toString() != "") {
                    binding.bvLogin.setBackgroundResource(R.drawable.login_background2)
                }
            }
        })
 ```
## ✅Step3
### ✔***DataBinding***
```kotlin
  val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.user = User(
            "SOPTHub", "이름", "나이", "MBTI", "짱구",
            "6", "CUTE", "짱구는 흰둥이를 좋아해"
        )

        binding.face = Databinding_image("https://mblogthumb-phinf.pstatic.net/MjAxODEyMDVfMjY5/MDAxNTQ0MDA3NDgyNjgw.v21vfp4yFzGtYlNrFPeo7Cxkd6ZVa3ZNKeRwZe5l3e0g.y2pAI3tJYWq04q_FwbVgTOoTVo9bKcwISdhj9EAxNYgg.GIF.nang723/IMG_0834.GIF?type=w800")


        binding.ivGithubIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dabinKim-0318"))
            startActivity(intent)
        }
```
```kotlin
data class User(
    val tv_title: String,
    val tv_name: String,
    val tv_age: String,
    val tv_mbti: String,
    val tv_name2: String,
    val tv_age2: String,
    val tv_mbti2: String,
    val tv_like: String,
)
```
```kotlin
class Databinding_image(val profile: String) {
    object MyBind {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImageUrl(view: ImageView, profile: String) {

            Glide.with(view.context)
                .load(profile)
                .into(view)
        }
    }
}
```
### ✔setOnClickListener
 setOnClickListener를 람다식으로 간결하게 표현할 수 있는 이유: SAM(Single Abstract Method) 변환
 구현하는 인터페이스(View.OnClickListener)에 구현하는 메소드가 하나(onClick)뿐일때는 이를 람다식으로 변경할 수 있습니다. 

## ✅배운 것
- fade_in fade_out 애니메이션 구현하는 법 
- 이미지에 gif 넣는 법
- isNullOrEmpty()가 string타입이 아닌 editable타입에서도 사용할 수 있다는 것
- startActivityForResult가 deprecated된 거 흐린 눈 하고 있었는데..ㅎㅎ registerForActivityResult로 데이터 리턴 받는 법을 배움
- addTextChangedListener로 editText 입력값 실시간으로 이벤트 처리하는 법
- Data Binding사용!!
