import collections

# @include
MatchResult = collections.namedtuple('MatchResult',
                                     ('winning_team', 'losing_team'))


def can_team_a_beat_team_b(matches, team_a, team_b):
    def build_graph():
        graph = collections.defaultdict(set)
        for match in matches:
            graph[match.winning_team].add(match.losing_team)
        return graph

    def is_reachable_dfs(graph, curr, dest, visited=set()):
        if curr == dest:
            return True
        elif curr in visited or curr not in graph:
            return False
        visited.add(curr)
        return any(is_reachable_dfs(graph, team, dest) for team in graph[curr])

    return is_reachable_dfs(build_graph(), team_a, team_b)
# @exclude


def check(matches, team_a, team_b):
    def build_graph():
        graph = collections.defaultdict(set)
        for match in matches:
            graph[match.winning_team].add(match.losing_team)
        return graph

    def is_reachable_bfs(graph, curr, dest, visited=set()):
        frontier = collections.deque([curr])
        visited.add(curr)
        while frontier:
            just_reached = frontier.popleft()
            if just_reached == dest:
                return True
            elif just_reached in graph:
                for team in graph[just_reached]:
                    if team not in visited:
                        visited.add(team)
                        frontier.append(team)
        return False

    assert can_team_a_beat_team_b(matches, team_a, team_b) == is_reachable_bfs(
        build_graph(), team_a, team_b)


def main():
    matches = [
        MatchResult('Texas', 'Cal'), MatchResult('Cal', 'Stanford'),
        MatchResult('Stanford', 'Texas'), MatchResult('Stanford', 'MIT'),
        MatchResult('Stanford', 'CIT'), MatchResult('UCLA', 'USC')
    ]
    check(matches, 'Texas', 'MIT')
    check(matches, 'Cal', 'UCLA')


if __name__ == '__main__':
    main()
