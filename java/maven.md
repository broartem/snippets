# Maven build tool snippets
Resume building project from a point of failure (mind the colon before module name), see https://stackoverflow.com/questions/2902322 for details:
```bash
maven -rf :<module name> clean install
```
