# NodeSeekBar
A custom seekbar with node-circles on its progress,it can be made as a  radio view, just be the same with  the view in IPHONE
Show
------
![仿IPHONE的NodeSeekBar](https://github.com/shang1101/NodeSeekBar/blob/master/nodeseekbar.gif)
Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.shang.designview</groupId>
  <artifactId>nodeseekbar</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
compile 'com.shang.designview:nodeseekbar:1.0.0'
```
or Eclipse:
   Go home,you are drunked!
Usage
------
in xml:
```xml
<com.shang.nodeseekbar.NodeSeekBar
         android:layout_below="@+id/nodeseekbar"
         android:id="@+id/attr_nodeseekbar"
         android:layout_marginTop="100dp"
         android:minHeight="1dp"
         android:maxHeight="1dp"
         android:max="100"
         android:progressDrawable="@drawable/seekbar_bg"
         android:thumb="@drawable/thumb"
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         shang:node_radius="10"
         shang:node_color="#132123" 
         shang:node_number="6"/>
```
or in java:
```java
 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NodeSeekBar nodeSeekBar = (NodeSeekBar)findViewById(R.id.nodeseekbar);
        nodeSeekBar.setNodeRadius(20);
        nodeSeekBar.setNodeCount(5);
        nodeSeekBar.setOnChoiceListener(new NodeSeekBar.OnSeekChoiceListener() {
            @Override
            public void onSeekChoosen(int index) {
                Toast.makeText(getApplicationContext(),"You are now choosing position ："+index,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```
License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
FeedBack
-------
any question,please send email to me (920621870@qq.com).