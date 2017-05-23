curl --output app.zip https://codeload.github.com/citrusframework/todo-demo-app/zip/master && unzip app.zip && rm app.zip
mv todo-demo-app-master app

curl --output scenarios.zip https://codeload.github.com/christophd/citrus-katacoda-scenarios/zip/master && unzip scenarios.zip && rm scenarios.zip
mv citrus-katacoda-scenarios-master/citrus-http/assets app-tests
rm -rf citrus-katacoda-scenarios-master