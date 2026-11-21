# slf4jloggerconfigurator
 ロガーにクラス名を自動設定する。 クラスにアノテーションが指定されている場合、そのクラスのログは出さない。

## RPMパッケージ
AlmaLinux 8/9/10向けにrpm化できる(依存のslf4j-apiを同梱)。

    ./build-rpm.sh    # 変更をコミットしてから実行。成果物: rpmbuild/RPMS/noarch/
    sudo rpm -ivh rpmbuild/RPMS/noarch/slf4jloggerconfigurator-*.rpm

`/usr/share/slf4jloggerconfigurator/maven-repo` に、本ライブラリとslf4j-apiを含むfile形式のMavenリポジトリが配置される。

### 利用側(recutil等)のpom.xmlへの記載
上記リポジトリを `<repositories>` に追加し、依存を宣言する。

```xml
<repositories>
	<repository>
		<id>local-rpm</id>
		<url>file:///usr/share/slf4jloggerconfigurator/maven-repo</url>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>
</repositories>
<dependencies>
	<dependency>
		<groupId>slf4jloggerconfigurator</groupId>
		<artifactId>slf4jloggerconfigurator</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```
