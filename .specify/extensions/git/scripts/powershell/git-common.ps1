#!/usr/bin/env pwsh
# Git-specific common functions for the git extension.
# Extracted from scripts/powershell/common.ps1 — contains only git-specific
# branch validation and detection logic.

function Test-HasGit {
    param([string]$RepoRoot = (Get-Location))
    try {
        if (-not (Test-Path (Join-Path $RepoRoot '.git'))) { return $false }
        if (-not (Get-Command git -ErrorAction SilentlyContinue)) { return $false }
        git -C $RepoRoot rev-parse --is-inside-work-tree 2>$null | Out-Null
        return ($LASTEXITCODE -eq 0)
    }
    catch {
        return $false
    }
}

function Get-SpecKitEffectiveBranchName {
    param([string]$Branch)
    if ($Branch -match '^([^/]+)/([^/]+)$') {
        return $Matches[2]
    }
    return $Branch
}

function Test-FeatureBranch {
    param(
        [string]$Branch,
        [bool]$HasGit = $true
    )

    # For non-git repos, we can't enforce branch naming but still provide output
    if (-not $HasGit) {
        Write-Warning "[specify] Warning: Git repository not detected; skipped branch validation"
        return $true
    }

    $raw = $Branch
    $slug = Get-SpecKitEffectiveBranchName $raw

    $isConstitutionStyle = $raw -match '^(feature|fix|chore)\/[a-z0-9]+(?:-[a-z0-9]+)*$'
    $isLegacySequential = $slug -match '^[0-9]{3,}-'
    $isLegacyTimestamp = $slug -match '^\d{8}-\d{6}-'
    if (-not $isConstitutionStyle -and -not $isLegacySequential -and -not $isLegacyTimestamp) {
        [Console]::Error.WriteLine("ERROR: Not on a feature branch. Current branch: $raw")
        [Console]::Error.WriteLine("Feature branches should be named like: feature/your-feature, fix/your-fix, or chore/your-task")
        return $false
    }
    return $true
}
