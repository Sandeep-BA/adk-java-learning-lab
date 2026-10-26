"""Build and verify the public learner HTML. Author: Sandeep Belgavi."""

import argparse
import html
import re
from html.parser import HTMLParser
from pathlib import Path
from urllib.parse import unquote, urlsplit

import markdown

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "learn"


def page(title, content, prefix):
    return f'''<!doctype html>
<!-- Generated from docs by scripts/build_docs.py. Author: Sandeep Belgavi. -->
<html lang="en"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="author" content="Sandeep Belgavi"><title>{html.escape(title)} · Belgavi's Lab</title>
<link rel="stylesheet" href="{prefix}assets/site-content.css"></head><body>
<a class="skip" href="#main">Skip to content</a><header><nav><a href="{prefix}LEARN.html">BELGAVI / AI LAB</a>
<span>ADK JAVA LEARNING LAB</span></nav></header><main id="main">{content}</main>
<footer>By Sandeep Belgavi · Original examples under MIT · Independent educational project</footer></body></html>'''


class Links(HTMLParser):
    def __init__(self):
        super().__init__()
        self.links = []
        self.ids = set()

    def handle_starttag(self, tag, attrs):
        attrs = dict(attrs)
        if "id" in attrs:
            self.ids.add(attrs["id"])
        for key in ("href", "src"):
            if key in attrs:
                self.links.append(attrs[key])


def build(check=False):
    expected = {}
    cards = []
    documents = sorted((ROOT / "docs").glob("*.md"))
    for source in documents:
        text = source.read_text(encoding="utf-8")
        title = text.splitlines()[0].removeprefix("# ")
        renderer = markdown.Markdown(extensions=["fenced_code", "tables", "toc"])
        body = renderer.convert(text)
        body = re.sub(
            r'href="([^":]+)\.md(#[^"]*)?"',
            lambda match: f'href="{match[1]}.html{match[2] or ""}"',
            body,
        )
        toc = '<nav class="toc" aria-label="On this page"><strong>On this page</strong>'
        toc += renderer.toc + "</nav>"
        h1_end = body.index("</h1>") + len("</h1>")
        body = body[:h1_end] + toc + body[h1_end:]
        body += '<nav class="next"><a href="../LEARN.html">All lessons</a>'
        body += '<a href="references.html">References</a></nav>'
        expected[OUT / f"{source.stem}.html"] = page(title, body, "../")
        if source.stem[:2].isdigit():
            cards.append(
                f'<a class="card" href="learn/{source.stem}.html">'
                f"<h2>{html.escape(title)}</h2><p>Run · inspect · test · extend</p></a>"
            )
    index = (
        """<p class="eyebrow">LEARN BY RUNNING THE CODE</p><h1>ADK Java.<br><em>Practical foundations.</em></h1>
<p class="lead">Build a small help-desk agent for Belgavi's Book Store and understand its boundaries.</p>
<p>By Sandeep Belgavi</p><nav class="toc"><a href="README.md">Setup instructions</a><a href="#lessons">Lessons</a>
<a href="learn/architecture.html">Architecture</a><a href="learn/references.html">References</a><a href="learn/troubleshooting.html">Troubleshooting</a></nav>
<aside>Default runs use the real ADK framework with a simulated model and synthetic data. No model account is required.</aside>
<section><h2>Follow one request</h2><div class="flow" aria-label="A message enters the ADK runner, which uses the model and validated tools with session state">
<span>User message</span><b>→</b><span>ADK runner</span><b>↔</b><span>Model</span><b>↔</b><span>Validated tools</span></div>
<p>The session carries conversation context. Application code validates inputs and enforces tool policy.</p></section>
<section id="lessons"><h2>Start here</h2><div class="grid">"""
        + "".join(cards)
        + "</div></section>"
    )
    expected[ROOT / "LEARN.html"] = page("Start learning", index, "")
    for path, text in expected.items():
        if check:
            assert path.is_file() and path.read_text(encoding="utf-8") == text, (
                f"Regenerate {path.name}"
            )
        else:
            path.parent.mkdir(parents=True, exist_ok=True)
            path.write_text(text, encoding="utf-8", newline="\n")
    for path, text in expected.items():
        parser = Links()
        parser.feed(text)
        for link in parser.links:
            url = urlsplit(link)
            if url.scheme or url.netloc:
                continue
            target = (path.parent / unquote(url.path)).resolve() if url.path else path
            assert target.is_relative_to(ROOT), f"Link escapes repository: {link}"
            assert target.is_file(), f"Missing local link in {path.name}: {link}"
            if url.fragment and target.suffix == ".html":
                dest = Links()
                dest.feed(target.read_text(encoding="utf-8"))
                assert url.fragment in dest.ids, f"Missing anchor: {link}"
    print(
        f"{'Verified' if check else 'Built'} {len(expected)} pages and their local links."
    )


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--check", action="store_true")
    build(parser.parse_args().check)
