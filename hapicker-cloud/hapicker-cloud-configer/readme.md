###Config支持我们使用的请求的参数规则：

/ { 应用名 } / { 环境名 } [ / { 分支名 } ]
/ { 应用名 } - { 环境名 }.yml
/ { 应用名 } - { 环境名 }.properties
/ { 分支名 } / { 应用名 } - { 环境名 }.yml
/ { 分支名 } / { 应用名 } - { 环境名 }.properties

>第一个规则的分支名是可以省略的，默认是master分支
无论你的配置文件是properties，还是yml，只要是应用名+环境名能匹配到这个配置文件，那么就能取到
如果是想直接定位到没有写环境名的默认配置，那么就可以使用default去匹配没有环境名的配置文件
使用第一个规则会匹配到默认配置
如果直接使用应用名来匹配，会出现404错误，此时可以加上分支名匹配到默认配置文件
如果配置文件的命名很由多个-分隔，此时直接使用这个文件名去匹配的话，会出现直接将内容以源配置文件内容直接返回，内容前可能会有默认配置文件的内容