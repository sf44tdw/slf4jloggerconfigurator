Name:           slf4jloggerconfigurator
Version:        0.0.1
Release:        1%{?dist}
Summary:        ロガーにクラス名を自動設定するJavaライブラリ
License:        GPLv3+
BuildArch:      noarch
Source0:        %{name}-%{version}.tar.gz
BuildRequires:  maven
BuildRequires:  java-devel

%global __jar_repack %{nil}
%global debug_package %{nil}
%global mavenrepo %{_datadir}/%{name}/maven-repo

%description
ロガーにクラス名を自動設定する。クラスにアノテーションが指定されている場合、そのクラスのログは出さない。
依存ライブラリ(slf4j-api)を含むfile形式のMavenリポジトリを %{mavenrepo} に配置する。
利用側のビルドでは、このディレクトリをfile://形式のリポジトリとして指定すると、ネットワーク無しで解決できる。

%prep
%setup -q

%build
mvn -B -DskipTests -Dmaven.repo.local=$PWD/.m2repo install

%install
R=%{buildroot}%{mavenrepo}
for D in slf4jloggerconfigurator/slf4jloggerconfigurator/0.0.1-SNAPSHOT \
         org/slf4j/slf4j-api/1.7.25 org/slf4j/slf4j-parent/1.7.25; do
    install -d $R/$D
    install -m 644 $(ls .m2repo/$D/*.jar .m2repo/$D/*.pom 2>/dev/null) $R/$D/
done

%files
%license LICENSE
%doc README.md
%{_datadir}/%{name}

%changelog
* Sat Nov 21 2026 slf4jloggerconfigurator <slf4jloggerconfigurator@localhost> - 0.0.1-1
- rpm化。
