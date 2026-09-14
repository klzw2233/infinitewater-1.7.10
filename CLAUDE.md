# CLAUDE.md

仓库级规则。与用户全局 `CLAUDE.md` 冲突时，以本文件为准。

本仓库是 Minecraft Forge 1.7.10 模组（GTNH 构建脚本）。包名 `com.klzw2233.infinitewater`。

---

## 分支

| 分支 | 角色 |
|------|------|
| `schema` | 发布分支 |
| `schema-dev` | 开发分支 |
| `main` | 历史默认分支，不要往这里合新功能 |

开发从当前的 `schema`（或已同步的 `schema-dev`）拉新分支。不要在落后于 `schema` 的 `schema-dev` 上继续改。

合入发布分支必须走 GitHub Pull Request，base 为 `schema`。

---

## 流程走不通时：停下来

**遇到流程走不通时，不要自己决定绕过去。停下来告诉 user，给出建议，让 user 做决定。**

禁止自行采取的绕过手段包括（不限于）：

- `gh pr merge --admin`
- 本地 merge / fast-forward 后直接 push 到受保护分支
- `git push --force` / `--force-with-lease` 到共享分支
- `git reset --hard` 丢掉 user 还可能需要的提交（user 明确要求撤销时除外）
- 改 remote URL、关 hook、`--no-verify`、改 branch protection 来「先弄上去」
- 用 SSH 替换 HTTPS（或反过来）只为了绕过认证失败——先告诉 user 认证失败了

正确做法：

1. 说明卡在哪（命令、错误原文、保护规则）。
2. 给 1–3 个可行选项（例如：网页上点 Merge、加 reviewer、用 `--admin`、改流程）。
3. 等 user 选。在此之前不要继续推进那条被拦住的路径。

### 常见场景

**PR 被 branch protection 拦住**（required checks、required reviews、「必须走 PR」）

- 不要 `--admin`，不要改在本地合再 push。
- 告诉 user：PR 链接、拦住的原因、需要人在 GitHub 上做什么。

**Git 认证失败**（HTTPS token、SSH、credential helper）

- 不要 silently 换协议再 push，除非 user 已经知道并同意。
- 说明失败原因和建议（SSH / `gh auth` / PAT），让 user 决定。

**构建 / 测试 / CI 失败**

- 不要为了「先合上去」跳过检查。
- 报告失败，给修复方案，等确认。

**rebase / merge 冲突、fetch 拉不下来、权限不够**

- 停。说明冲突或权限问题。不要 force。

---

## Git

- 功能/修复从 `schema` 拉 `feat/*`、`fix/*`、`docs/*` 等分支。
- 不要直接在 `schema` 或 `main` 上改。
- 提交信息用英文、祈使句，说明为什么。
- push 走 PR。合入 `schema` 由 GitHub PR 完成；合完后再 `fetch` + `pull` 到本地。
- 远程 `origin` 是 `https://github.com/klzw2233/infinitewater-1.7.10.git`。若 HTTPS push 失败，告诉 user，不要擅自改 remote。

---

## 实现

- 先读相关源码和 `docs/` 里已有的 review，再改。
- 这是 1.7.10 Forge：没有 1.12+ 的 `RegistryEvent`、没有 `Capability` 作为流体主路径。流体走 `IFluidHandler` / `FluidStack` / `FluidContainerRegistry`。
- 不要为了「以后」加空的 registry、proxy、网络类。现有空壳能删就删。
- 改 NBT 必须考虑旧存档和物品掉落两条路径（`writeToNBT` vs `writeCustomNBT`）。
- 非平凡逻辑改完后，说明如何在游戏里验证（1.7.10 没有现成单元测试框架时，写清手动测试步骤）。

已知问题与建议顺序见 `docs/review-2026-09-14.md`。动代码前先看它，避免重复踩坑。
