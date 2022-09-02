# Protocol Buffers

### protocol Buffer fields

```protobuf
message SearchRequest{   
	required string query = 1;    
	optional int32 page_number = 2;    
	optional int32 result_per_page = 3    
}
```
   
SearchRequest는 name-value 쌍으로 이루어진 3개의 필드로 이루어져있다.   
아래 예제는 단일값 (scalar type)으로 만 이루어져 있지만 enumerations을 사용해서 composite 타입도 정의 할 수 있다.    
또한, 필드를 보면 각 필드는 unique한 넘버로 이루어져 있는걸 볼 수 있는데, 이는 메시지의 이진 형식 필드를 식별하는데 사용되며 메시지가 한번 이상 사용되었다면 바뀌면 안된다.   
메시지 unique number 1 ~ 15번은 인코딩 하는데 1byte 가 필요하며 16~2047은 2byte가 필요하다.   
따라서, 자주 사용되는 메시지 요소에 대해서 1~15번을 예약해야한다. unique number은 1 ~ 2^29-1 만큼 예약할 수 있다. 또한, 19000 ~ 19999는 사용 할 수 없는데, 이는 protocol Buffer의 예약어 이기 때문이다.   
   
message 필드는 아래의 요소들로 이루어진다.
``` 
required : 메시지 타입은 이 요소가 정확히 하나 있어야한다. required타입의 필드는 메시지에서 반드시 전송되어야한다.
optional : 메시지 타입은 이 요소가 없거나 여러개 있을수있다. optional타입의 필드는 전송될수도 안될수도 있다.
repeated : 이 타입은 여러번 반복 가능하며, 반복되는 값의 순서는 유지된다.
```
   
또한, 단일값 타입의 repeated 필드(int32, int64, enum)은 효과적으로 인코딩 되지않는데, 이 를 방지하기 위해 [packed = true] 옵션을 줘야한다.

```protobuf
	repeated int32 samples = 4 [packed = true];
	repeated ProtoEnum results = 5 [packed = true];
```
    
required타입의 필드는 특히 조심히 작성해햐하는데, required필드를 보내고싶지 않아서 optional로 바꾼경우, 바뀐사실을 모르는 수신자는 required필드가 비어있다고 생각하고 잘못된 요청으로 인식해 이를 drop할 것이다.   
   
### scalar value types of .proto
다음은 .proto 파일에서 사용할 수 있는 단일 타입이다.    
[Language Guide  |  Protocol Buffers  |  Google Developers](https://developers.google.com/protocol-buffers/docs/proto#scalar)   
   
### multiple messages 
또한, 여러개의 메시지를 하나의 .proto file에 정의할 수 있는데, 이는 연관된 메시지를 표현하는데 효과적이다.  예시는 아래와 같다.   
```protobuf
message SearchRequest{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3;
}

message SearchResponse{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3;
}
```
   
.proto 파일에는 가능한한 적은 메시지를 포함하는게 좋은데, 많은 메시지를 포함할 경우 의존성이 증가할 수 있다.   
   
### reserved field
.proto 파일의 필드를 없애거나 주석처리한 후, 나중에 해당 식별자를 재사용할 경우 이는 심각한 에러를 야기 할 수 있다. 문제는 protoc 컴파일러는 이 를 잡아주지 않는다는 것인데, 이 상황을 막기 위해서, reserved 필드를 사용할 수 있다.   
```protobuf
message Foo{
	reserved 2, 15, 9 to 11;
	reserved "foo", "bar";
}
```
   
### Default Values
message에 optional 필드는  Default value를 줄 수있는데, Default value를 줄 경우, parsing된(객체로 변환된) message에 접근하면 Default value를 얻을 수 있다.      예시는 다음과 같다.   
```protobuf
message SearchRequest{
	required string query = 1;
	optional int32 page_number = 2;
	optional int32 result_per_page = 3 [default = 10];
}
```
   
### Enumerations
Enum 타입의 형식을 선언하고 싶을때, enum을 사용할 수 있다.  예시는 다음과 같다.
```protobuf
enum Corpus{
	CORPUS_UNSPECIFIED = 0;
	CORPUS_UNIVERSAL = 1;
	CORPUS_WEB = 2;
	CORPUS_IMAGES = 3;
	CORPUS_LOCAL = 4;
	CORPUS_NEWS = 5;
	CORPUS_PRODUCTS = 6;
	CORPUS_VIDEO = 7;
}

message ExampleMessage{
	required string someField = 1;
	optional Corpus corpus = 2 [default = CORPUS_UNSPECIFIED];
}
```
   
위 예시를 보면, Corpus Enum타입에 선언된 값중 하나를 사용할 수 있다. 또한, Enum 내부에 식별값으로 같은 값을 사용하고 싶을 수 있는데, 이 를 위해선 allow_alias option을 true로 주면 된다. 예시는 아래와 같다.   
```protobuf
enum Corpus{
	option allow_alias = true;
	CORPUS_UNSPECIFIED = 1;
	CORPUS_UNIVERSAL = 1;
}
```
   
또한, protoc compiler는 enum타입을 Java, C++의 enum에 상응하게 컴파일 해준다.    
Enum또한 마찬가지로 reserved values를 사용해야하며 사용하는것이 권장된다.
```protobuf
enum Foo{
	reserved 2, 15, 9 to 11, 40 to max;
	reserved "FOO", "BAR";
}
```

