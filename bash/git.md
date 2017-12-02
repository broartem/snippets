# Git shell snippets
Delete remote branch:
```bash
git push origin --delete <branchName>
```
Merge several latest commits into one:
```bash
git reset --soft HEAD~3 && git commit
```
Apply patch on Windows:
```bash
git apply --ignore-space-change --ignore-whitespace patch.txt
```
Discard all the changes in the working directory:
```bash
git checkout -- .
```
To get the list of stashes that are still in your repository, but not reachable any more (e.g. dropped, see http://stackoverflow.com/questions/89332):
```bash
git fsck --unreachable | grep commit | cut -d" " -f3 | xargs git log --merges --no-walk --grep=WIP
```
