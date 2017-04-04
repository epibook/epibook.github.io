_foo()
{
    local cur=${COMP_WORDS[COMP_CWORD]}
    COMPREPLY=( $(compgen -W "alpha beta bar baz" -- $cur) )
}
complete -F _foo foo
