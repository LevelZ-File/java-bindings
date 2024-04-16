git config --local user.email "action@github.com"
git config --local user.name "GitHub Action"
git fetch origin gh-pages

if [ ! -d "docs" ]; then
  mkdir docs
fi;

cp -Rfv build/docs/javadoc/* ./docs/
cp -Rfv src/main/javadoc/favicon.ico ./docs/

git switch -f gh-pages

for dir in ./*
do
  if [ "$dir" == "./docs" ]; then
    continue
  fi

  rm -rf "$dir"
done

cp -Rfv ./docs/* ./
rm -rf ./docs

git add .
git commit -m "Update JavaDocs ($1)"
git push -f origin gh-pages